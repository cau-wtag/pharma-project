import java.time.LocalDate;

public class TestAllExceptions {

    public static void main(String[] args) {
        
        System.out.println("TESTING DOMAIN EXCEPTIONS");

        // Test 1: Controlled Substance Violation
        try {
            checkDuplicatePrescription("RX-1001", true);
        } catch (PrescriptionReuseException e) {
            System.err.println("CAUGHT RULE 1: " + e.getMessage());
        }

        // Test 2: Expired Stock Violation
        try {
            checkBatchExpiry("BATCH-99", LocalDate.of(2025, 1, 1)); // Past date
        } catch (ExpiredStockException e) {
            System.err.println("CAUGHT RULE 2: " + e.getMessage());
        }

        // Test 3: Drug Interaction Violation
        try {
            checkInteraction("Aspirin", "Warfarin");
        } catch (DrugInteractionException e) {
            System.err.println("CAUGHT RULE 3: " + e.getMessage());
        }
    }

    // --- Helper Validation Methods ---

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