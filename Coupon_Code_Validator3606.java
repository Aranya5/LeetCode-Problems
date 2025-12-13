import java.util.*;

class Coupon_Code_Validator3606 {

    public List<String> validateCoupons(String[] code, String[] businessLine, boolean[] isActive) {
        List<String> electronics = new ArrayList<>();
        List<String> grocery = new ArrayList<>();
        List<String> pharmacy = new ArrayList<>();
        List<String> restaurant = new ArrayList<>();
        
        for (int i = 0; i < code.length; i++) {
            if (!isActive[i] || code[i].isEmpty()) continue;
            
            boolean validCode = true;
            for (char c : code[i].toCharArray()) {
                if (!Character.isLetterOrDigit(c) && c != '_') {
                    validCode = false;
                    break;
                }
            }
            
            if (validCode) {
                if (businessLine[i].equals("electronics")) {
                    electronics.add(code[i]);
                }
                else if (businessLine[i].equals("grocery")){
                    grocery.add(code[i]);
                }
                else if (businessLine[i].equals("pharmacy")){
                    pharmacy.add(code[i]);
                }
                else if (businessLine[i].equals("restaurant")){
                    restaurant.add(code[i]);
                }
            }
        }
        
        Collections.sort(electronics);
        Collections.sort(grocery);
        Collections.sort(pharmacy);
        Collections.sort(restaurant);
        
        List<String> result = new ArrayList<>();
        result.addAll(electronics);
        result.addAll(grocery);
        result.addAll(pharmacy);
        result.addAll(restaurant);
        
        return result;
    }

    // -------- MAIN METHOD --------
    public static void main(String[] args) {
        Coupon_Code_Validator3606 sol = new Coupon_Code_Validator3606();

        String[] code = {
            "TV_20", "GROC123", "MED#45", "FOOD99", "PHONE_1", ""
        };

        String[] businessLine = {
            "electronics", "grocery", "pharmacy", "restaurant", "electronics", "grocery"
        };

        boolean[] isActive = {
            true, true, true, true, false, true
        };

        List<String> validCoupons = sol.validateCoupons(code, businessLine, isActive);

        System.out.println("Valid Coupons:");
        for (String c : validCoupons) {
            System.out.println(c);
        }
    }
}
