public interface DosageForm {
    String getFormName();
    String getMeasurementDetails();
}

class Tablet implements DosageForm {
    private final int pillCount; 
    private final int strengthMg;

    public Tablet(int pillCount, int strengthMg) {
        this.pillCount = pillCount;
        this.strengthMg = strengthMg;
    }

    @Override
    public String getFormName() {
        return "Tablet";
    }

    @Override
    public String getMeasurementDetails() {
        return pillCount + " pills, " + strengthMg + " mg each";
    }
}

class Liquid implements DosageForm {
    private final double volumeMl; 
    private final String concentrationMgPerMl;

    public Liquid(double volumeMl, String concentrationMgPerMl) {
        this.volumeMl = volumeMl;
        this.concentrationMgPerMl = concentrationMgPerMl;
    }

    @Override
    public String getFormName() {
        return "Liquid";
    }

    @Override
    public String getMeasurementDetails() {
        return volumeMl + " mL [Conc: " + concentrationMgPerMl + "]";
    }
}