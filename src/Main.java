import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        String pass = "";
        int strength = 0;

        System.out.printf("Introduzca su contraseña: ");
        //If no content, repeat
        while(pass.isEmpty()){
            Scanner sc = new Scanner(System.in);
            pass = sc.nextLine();
            if(pass.isEmpty()){
                System.out.flush();
                System.out.printf("Por favor introduzca al menos un caracter: ");
            }
        }
        //Calculating strength of pass
        strength = calculateStrength(pass);
        System.out.println("La contraseña tiene una fortaleza de: "+ strength);

        if(strength < 8){
            Scanner scanner = new Scanner(System.in);

            System.out.println("La contraseña es débil. ¿Desea continuar? (s/n)");
            String response = scanner.nextLine();
            if (!response.equalsIgnoreCase("s")) {
                System.out.println("Contraseña rechazada.");
                return;
            }
        }
        System.out.println("Contraseña aceptada.");
    }
    
    public static int getLength(String password) {
        int length = password.length();
        int score = 0;
        if (length >= 9 && length <= 12) {
            score = 2;
        } else if (length > 12) {
            score = 3;
        }

        return score;
    }

    public static boolean checkIfLetter(char password) {
        if (Character.isLetter(password)) {
            return true;
        }else{
            return false;
        }
    }
    public static boolean checkIfLower(char charPass) {
        if (Character.isLowerCase(charPass)) {
            return true;
        }else{
            return false;
        }
    }
    public static boolean checkIfUpper(char charPass) {
        if (Character.isUpperCase(charPass)) {
            return true;
        }else{
            return false;
        }
    }
    public static boolean checkIfDigit(char charPass) {
        if (Character.isDigit(charPass)) {
            return true;
        }else{
            return false;
        }
    }
    public static int calculateStrength(String password) {
        int score = 0;
        boolean hasLetter = false, hasLowerCase = false, hasUpperCase = false, hasDigit = false, hasSymbols = false;
        char[] passwordToChars = password.toCharArray();

        score += getLength(password);
        for(int i = 0; i < passwordToChars.length; i++){
            
            //Checking letter points
            if(checkIfLetter(passwordToChars[i])) {
                if (!hasLetter) {
                    hasLetter = true;
                    score += 1;

                }

                if (!hasLowerCase && checkIfLower(passwordToChars[i])) {
                    hasLowerCase = true;
                    if(hasUpperCase){
                        score += 2;
                    }
                }

                if (!hasUpperCase && checkIfUpper(passwordToChars[i])) {
                    hasUpperCase = true;
                    if(hasLowerCase){
                        score += 2;
                    }
                }


            }
            //Checking digit 
            else if (checkIfDigit(passwordToChars[i]) && !hasDigit) {
                hasDigit = true;
                score += 1;
            }else if(!hasSymbols){
                hasSymbols = true;
                score += 2;
            }
        }
        //Check if conditions accomplished
        if(hasSymbols && hasLetter && hasLowerCase && hasUpperCase && hasDigit && password.length() > 12){
            score += 1;
        }
        return score;

    }
}