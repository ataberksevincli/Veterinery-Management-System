package dev.patika.veterinary.core.utilies;

public class Msg {
    public static final String CREATED = "Kayıt eklendi";
    public static final String VALIDATE_ERROR = "Veri doğrulama hatası";
    public static final String OK = "İşlem başarılı";
    public static final String NOT_FOUND = "Veri bulunamadı";

    public static final String EXIST_VACCINE = "A vaccine with the same name, code, and overlapping protection dates already exists for this animal.";
    public static final String NO_ANIMAL = "There is no animal with this name.";
    public static final String NO_CUSTOMER_ANIMAL = "There are no animals with this customer name.";

    public static final String NO_DOCTOR_FOUND = "No doctor found with this id";

    public static final String NO_ANIMAL_FOUND = "No animal found with this id\n";
    public static final String NO_CUSTOMERID_ANIMAL = "There are no animals with this customer Id.";
    public static final String DOCTOR_NOT_AVAILABLE = "The doctor is not available on this date.";

    public static final String ALREADY_AN_APPOINTMENT = "There is already an appointment for the doctor on the specified date and time.";
}

