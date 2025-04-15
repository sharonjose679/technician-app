package Utils;

public class WorkOrderResponseCustomClass {
    private final String workOrderId;
    private final int itemsCount;
    public WorkOrderResponseCustomClass(String workOrderId, int itemsCount)
    {
        this.workOrderId = workOrderId;
        this.itemsCount = itemsCount;
    }

    public String getWorkOrderId() {
        return workOrderId;
    }

    public int getItemsCount()
    {
        return itemsCount;
    }
}
