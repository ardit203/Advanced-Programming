package Exam.FirstMidterm.Task32NotSolved;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


interface ILocation{
    double getLongitude();

    double getLatitude();

    LocalDateTime getTimestamp();
}

class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String id) {
        super(String.format("User with id %s already exists", id));
    }
}


class User{
    private String id;
    private String name;
    private List<ILocation> locations;
    private LocalDateTime caseDetected;
    private boolean isInfected;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
        this.locations = new ArrayList<>();
        this.caseDetected = null;
        this.isInfected = false;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<ILocation> getLocations() {
        return locations;
    }

    public LocalDateTime getCaseDetected() {
        return caseDetected != null ? caseDetected : LocalDateTime.MAX;
    }

    public void setLocations(List<ILocation> locations) {
        this.locations = locations;
    }

    public void setCaseDetected(LocalDateTime caseDetected) {
        this.caseDetected = caseDetected;
        this.isInfected = true;
    }

    public boolean isInfected() {
        return isInfected;
    }

    @Override
    public String toString() {
        return String.format("%s %s***", name, id.substring(0, 4));
    }
}


class StopCoronaApp {
    private Map<String, User> users;

    public StopCoronaApp() {
        this.users = new HashMap<>();
    }

    public void addUser(String name, String id) {
        User user = users.putIfAbsent(id, new User(id, name));

        if (user != null) {
            throw new UserAlreadyExistException(id);
        }
    }

    public void addLocations(String id, List<ILocation> locations) {
        users.get(id).setLocations(locations);
    }

    public void detectNewCase(String id, LocalDateTime timestamp) {
        users.get(id).setCaseDetected(timestamp);
    }

    public void createReport() {
        users.values()
                .stream()
                .filter(User::isInfected)
                .sorted(Comparator.comparing(User::getCaseDetected))
                .forEach(user -> {
                    Map<User, Integer> direct = getDirectContacts(user);
                    List<User> indirect = getIndirectContacts(user);

                    System.out.printf("%s %s %s\n", user.getName(), user.getId(), user.getCaseDetected());
                    System.out.println("Direct contacts: ");
                    direct.entrySet()
                            .stream()
                            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                            .forEach(e -> System.out.printf("%s %d\n", e.getKey(), e.getValue()));
                    int sum = direct.values().stream().mapToInt(i -> i).sum();
                    System.out.printf("Count of direct contacts: %d\n", sum);
                    System.out.println("Indirect contacts: ");
                    indirect.forEach(System.out::println);
                    System.out.printf("Count of indirect contacts: %d\n", indirect.size());
                });
    }


    private Map<User, Integer> getDirectContacts(User user) {
        Map<User, Integer> result = new TreeMap<>(Comparator.comparing(User::getCaseDetected).thenComparing(User::getId));

        for (Map.Entry<String, User> entry : users.entrySet()) {
            if (entry.getValue() == user) {
                continue;
            }
            updateMap(result, user, entry.getValue());
        }

        return result.entrySet()
                .stream()
//                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));
    }

    private List<User> getIndirectContacts(User user) {
        Map<User, Integer> directContacts = getDirectContacts(user);

        List<Map<User, Integer>> directContactsOfDirectContacts = new ArrayList<>();

        for (User u : directContacts.keySet()) {
            directContactsOfDirectContacts.add(getDirectContacts(u));
        }

        return directContactsOfDirectContacts.stream()
                .flatMap(u -> u.keySet().stream())
                .filter(u -> !u.getId().equals(user.getId()) && !directContacts.containsKey(u))
                .distinct()
                .sorted(Comparator.comparing(User::getName).thenComparing(User::getId))
                .collect(Collectors.toList());
    }


    private static void updateMap(Map<User, Integer> result, User user1, User user2) {
        for (ILocation location1 : user1.getLocations()) {
            for (ILocation location2 : user2.getLocations()) {
                if (areCloseContacts(location1, location2)) {
                    result.merge(user2, 1, Integer::sum);
                }
            }
        }
    }

    private static boolean areCloseContacts(ILocation location1, ILocation location2) {
        double x = Math.abs(location1.getLatitude() - location2.getLatitude());
        double y = Math.abs(location1.getLongitude() - location2.getLongitude());
        double euclideanDistance = Math.sqrt((x * x) + (y * y));
        double timeDifferenceInMinutes = StopCoronaTest.timeBetweenInSeconds(location1, location2) / 60;

        return euclideanDistance <= 2 && timeDifferenceInMinutes <= 5;
    }
}



public class StopCoronaTest {
    
    public static double timeBetweenInSeconds(ILocation location1, ILocation location2) {
        return Math.abs(Duration.between(location1.getTimestamp(), location2.getTimestamp()).getSeconds());
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        StopCoronaApp stopCoronaApp = new StopCoronaApp();

        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] parts = line.split("\\s+");

            switch (parts[0]) {
                case "REG": //register
                    String name = parts[1];
                    String id = parts[2];
                    try {
                        stopCoronaApp.addUser(name, id);
                    } catch (UserAlreadyExistException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "LOC": //add locations
                    id = parts[1];
                    List<ILocation> locations = new ArrayList<>();
                    for (int i = 2; i < parts.length; i += 3) {
                        locations.add(createLocationObject(parts[i], parts[i + 1], parts[i + 2]));
                    }
                    stopCoronaApp.addLocations(id, locations);

                    break;
                case "DET": //detect new cases
                    id = parts[1];
                    LocalDateTime timestamp = LocalDateTime.parse(parts[2]);
                    stopCoronaApp.detectNewCase(id, timestamp);

                    break;
                case "REP": //print report
                    stopCoronaApp.createReport();
                    break;
                default:
                    break;
            }
        }
    }

    private static ILocation createLocationObject(String lon, String lat, String timestamp) {
        return new ILocation() {
            @Override
            public double getLongitude() {
                return Double.parseDouble(lon);
            }

            @Override
            public double getLatitude() {
                return Double.parseDouble(lat);
            }

            @Override
            public LocalDateTime getTimestamp() {
                return LocalDateTime.parse(timestamp);
            }
        };
    }
}


