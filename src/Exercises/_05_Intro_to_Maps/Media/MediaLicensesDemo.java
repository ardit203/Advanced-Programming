package Exercises._05_Intro_to_Maps.Media;

import java.util.Set;

public class MediaLicensesDemo {
    public static void main(String[] args) {
        Ledger<License> ledger = new Ledger<>();
        ledger.put(new License("Lo-Fi Beats", "music", "EU", 120_000, 0.0012));
        ledger.put(new License("Cooking B-Roll", "video", "US", 18_000, 0.02));
        ledger.put(new License("City Skyline", "photo", "EU", 7000, 0.15));
        ledger.put(new License("Nature Ambience", "music", "APAC", 90_000, 0.0014));
        ledger.put(new License("Interview Pack", "video", "EU", 9_500, 0.03));
        ledger.put(new License("Retro Poster", "photo", "US", 1500, 0.40));

        Set<String> categories = ledger.project(License::category);
        System.out.println("CATEGORIES: " + categories);

        System.out.println("\nMARK HIGH-VALUE (> 100.00):");
        ledger.forEachIf(l -> l.amount() > 100.0, l -> System.out.println("★ " + l.title()));
    }
}