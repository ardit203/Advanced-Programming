//package Exercises._06_Maps;
//
//import java.util.*;
//import java.util.stream.Stream;
//
//public class ChatbotTest2 {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        // Read LLMModelDetails properties
//        String modelName = scanner.next();
//        double inputTokenPrice = scanner.nextDouble();
//        double outputTokenPrice = scanner.nextDouble();
//        double mbAttachmentPrice = scanner.nextDouble();
//
//        LLMModelDetails llmModelDetails = new LLMModelDetails(modelName, inputTokenPrice, outputTokenPrice, mbAttachmentPrice);
//
//        // Read list of notSupportedFiles
//        scanner.nextLine(); // Consume newline
//        List<String> notSupportedFiles = Arrays.asList(scanner.nextLine().split(";"));
//
//        // Read allowedAttachmentsSize
//        int allowedAttachmentsSize = scanner.nextInt();
//        scanner.nextLine();
//
//        Chatbot chatbot = new Chatbot(llmModelDetails, notSupportedFiles, allowedAttachmentsSize);
//
//        while (scanner.hasNext()) {
//            String[] parts = scanner.nextLine().split(";");
//            String command = parts[0];
//
//            switch (command) {
//                case "addInteraction": {
//                    try {
//                        String userId = parts[1];
//                        String sessionId = parts[2];
//                        String question = parts[3];
//                        long timestampQuestion = Long.parseLong(parts[4]);
//                        String answer = parts[5];
//                        long timestampAnswer = Long.parseLong(parts[6]);
//                        int attachmentCount = Integer.parseInt(parts[7]);
//                        List<Attachment> attachments = new ArrayList<>();
//
//                        for (int i = 0; i < attachmentCount; i++) {
//                            String fileName = parts[8 + i * 2];
//                            int fileSize = Integer.parseInt(parts[9 + i * 2]);
//                            attachments.add(new Attachment(fileName, fileSize));
//                        }
//
//                        chatbot.addInteraction(userId, sessionId, question, timestampQuestion, answer, timestampAnswer, attachments);
//                    } catch (Exception e) {
//                        System.out.println(e.getMessage());
//                    }
//                    break;
//                }
//                case "printConversation": {
//                    String userId = parts[1];
//                    String sessionId = parts[2];
//                    chatbot.printConversation(userId, sessionId);
//                    break;
//                }
//                case "printSessionDetails": {
//                    String userId = parts[1];
//                    String sessionId = parts[2];
//                    chatbot.printSessionDetails(userId, sessionId);
//                    break;
//                }
//                case "printUserDetails": {
//                    String userId = parts[1];
//                    chatbot.printUserDetails(userId);
//                    break;
//                }
//                case "longestProcessingTimeInteractions": {
//                    chatbot.longestProcessingTimeInteractions();
//                    break;
//                }
//                case "mostExpensiveInteractions": {
//                    chatbot.mostExpensiveInteractions();
//                    break;
//                }
//                case "exit": {
//                    return;
//                }
//            }
//        }
//
//        scanner.close();
//    }
//}
