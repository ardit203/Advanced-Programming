package Exercises._06_Maps;

public class LLMModelDetails {
    String modelName;
    double inputTokenPrice;

    double outputTokenPrice;

    double mbAttachmentPrice;

    public LLMModelDetails(String modelName, double inputTokenPrice, double outputTokenPrice, double mbAttachmentPrice) {
        this.modelName = modelName;
        this.inputTokenPrice = inputTokenPrice;
        this.outputTokenPrice = outputTokenPrice;
        this.mbAttachmentPrice = mbAttachmentPrice;
    }
}