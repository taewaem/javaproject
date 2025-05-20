package store.product;

public class ProductDescription {
    private String summary;  // 간단 설명 (예: 체력 개선)
    private String details;
    private String usage;
    private String caution;

    public ProductDescription(String summary, String details, String usage, String caution) {
        this.summary = summary;
        this.details = details;
        this.usage = usage;
        this.caution = caution;
    }



    public String getSummary() { return summary; }
    public String getDetails() { return details; }
    public String getUsage() { return usage; }
    public String getCaution() { return caution; }
}
