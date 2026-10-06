public class Medication<T extends DosageForm> {
    private final String regNo;
    private final String name;
    private final T dosageForm;

    public Medication(String regNo, String name, T dosageForm) {
        this.regNo = regNo;
        this.name = name;
        this.dosageForm = dosageForm;
    }

    public String getRegNo() {
        return regNo;
    }

    public String getName() {
        return name;
    }

    public T getDosageForm() {
        return dosageForm;
    }
    @Override 
    public String toString() {
        return "Medication: " + name + " [Reg No: " + regNo + "] | Form: " + dosageForm.getFormName() + ", Details: " + dosageForm.getMeasurementDetails();
    }
}
