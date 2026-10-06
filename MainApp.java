import java.time.LocalDate;

public class MainApp {

    public static void main(String[] args) {
        
        System.out.println("TESTING  EXCEPTIONS");

        try {
            checkDuplicatePrescription("RX-1001", true);
        } catch (PrescriptionReuseException e) {
            System.err.println("CAUGHT RULE 1: " + e.getMessage());
        }

        try {
            checkBatchExpiry("BATCH-99", LocalDate.of(2025, 1, 1)); // Past date
        } catch (ExpiredStockException e) {
            System.err.println("CAUGHT RULE 2: " + e.getMessage());
        }

        try {
            checkInteraction("Aspirin", "Warfarin");
        } catch (DrugInteractionException e) {
            System.err.println("CAUGHT RULE 3: " + e.getMessage());
        }

        System.out.println("\n-----------------------------------------");

        
        System.out.println("---> 2. Testing Generic Medication Formulations...");

        // Liquid Generic Instance
        Medication<Liquid> amoxicillin = new Medication<>(
            "MOH-REG-2026-0412", 
            "Amoxicillin Oral Suspension", 
            new Liquid(150.0, "250mg/5mL")
        );

        // Tablet Generic Instance
        Medication<Tablet> ibuprofen = new Medication<>(
            "MOH-REG-2025-0891", 
            "Ibuprofen Extra Strength", 
            new Tablet(100, 400)
        );

        System.out.println(amoxicillin);
        System.out.println(ibuprofen);

    }


    public static void checkDuplicatePrescription(String rxId, boolean alreadyFulfilled) 
            throws PrescriptionReuseException {
        if (alreadyFulfilled) {
            throw new PrescriptionReuseException(
                "SECURITY ALERT: Prescription [" + rxId + "] was already fulfilled!"
            );
        }
    }

    public static void checkBatchExpiry(String batchId, LocalDate expiryDate) 
            throws ExpiredStockException {
        if (expiryDate.isBefore(LocalDate.now())) {
            throw new ExpiredStockException(
                "STOCK REJECTED: Batch [" + batchId + "] expired on " + expiryDate + "!"
            );
        }
    }

    public static void checkInteraction(String drugA, String drugB) 
            throws DrugInteractionException {
        if (drugA.equalsIgnoreCase("Aspirin") && drugB.equalsIgnoreCase("Warfarin")) {
            throw new DrugInteractionException(
                "CLINICAL ALERT: Dangerous interaction detected between " + drugA + " and " + drugB + "!"
            );
        }
    }
}