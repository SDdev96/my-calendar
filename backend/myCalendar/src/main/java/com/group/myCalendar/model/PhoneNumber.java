// package com.group.myCalendar.model;

// import java.time.LocalDate;

// import jakarta.persistence.*;
// import jakarta.validation.constraints.*;

// @SuppressWarnings("unused")
// @Entity
// @Table(name = "phone_number")
// public class PhoneNumber {

//     @Id
//     @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "phone_number_sequence")
//     @SequenceGenerator(name = "phone_number_sequence", sequenceName = "phone_number_sequence", allocationSize = 1)
//     private Long id;

//     @Pattern(regexp = "\\+?[0-9. ()-]{7,25}", message = "Invalid phone number format")
//     @Column(nullable = false)
//     private String number;

//     @ManyToOne
//     @JoinColumn(name = "person_id", nullable = false)
//     private Person person;

//     // CONSTRUCTORS
//     public PhoneNumber() {
//     }

//     public PhoneNumber(String number) {
//         setNumber(number);
//     }

//     // GETTERS
//     public Long getId() {
//         return id;
//     }

//     public String getNumber() {
//         return number;
//     }

//     public Person getPerson() {
//         return person;
//     }

//     // SETTERS
//     public void setId(Long id) {
//         this.id = id;
//     }

//     public void setNumber(String number) {
//         if (number == null || number.isEmpty()) {
//             throw new IllegalArgumentException("Phone number cannot be null or empty");
//         }
//         if (!number.matches("\\+?[0-9. ()-]{7,25}")) {
//             throw new IllegalArgumentException("Invalid phone number format: " + number);
//         }
//         this.number = number;
//     }

//     public void setPerson(Person person) {
//         if (person == null) {
//             throw new IllegalArgumentException("No person associated with this number");
//         }
//         if (this.person != null && !this.person.equals(person)) {
//             throw new IllegalArgumentException("This phone number is already associated with another person");
//         }
//         this.person = person;
//     }

//     @Override
//     public String toString() {
//         return "PhoneNumber{" +
//                 "id=" + id +
//                 ", number=" + number +
//                 ", person="
//                 + person.getName() + " " + person.getSurname() + ", "
//                 + person.getEmail() +
//                 '}';
//     }

//     public static void main(String[] args) {
//         try {
//             // Caso valido: Numero di telefono e persona associati correttamente
//             Person person = new Person(true, "Salvatore", "D'Ambrosio", LocalDate.of(1996, 5, 10), "sd.io@hotmail.com");
//             PhoneNumber validPhone = new PhoneNumber("+123456789");
//             validPhone.setPerson(person);
//             person.addPhoneNumber(validPhone);
//             System.out.println("Caso valido: ");
//             System.out.println(validPhone);
//             System.out.println(person);
//         } catch (IllegalArgumentException e) {
//             System.out.println("Errore nel caso valido: " + e.getMessage());
//         }

//         try {
//             // Caso errore: Numero di telefono nullo
//             PhoneNumber nullPhone = new PhoneNumber(null);
//         } catch (IllegalArgumentException e) {
//             System.out.println("Errore (numero nullo): " + e.getMessage());
//         }

//         try {
//             // Caso errore: Numero di telefono vuoto
//             PhoneNumber emptyPhone = new PhoneNumber("");
//         } catch (IllegalArgumentException e) {
//             System.out.println("Errore (numero vuoto): " + e.getMessage());
//         }

//         try {
//             // Caso errore: Numero di telefono con formato non valido
//             PhoneNumber invalidFormatPhone = new PhoneNumber("invalid123");
//         } catch (IllegalArgumentException e) {
//             System.out.println("Errore (formato non valido): " + e.getMessage());
//         }

//         try {
//             // Caso errore: Associare una persona nulla
//             PhoneNumber phoneWithNullPerson = new PhoneNumber("+987654321");
//             phoneWithNullPerson.setPerson(null);
//         } catch (IllegalArgumentException e) {
//             System.out.println("Errore (persona nulla): " + e.getMessage());
//         }

//         try {
//             // Caso valido: Due numeri di telefono associati alla stessa persona
//             Person person = new Person(false, "Maria", "Rossi", LocalDate.of(1995, 8, 20), "maria.rossi@example.com");
//             PhoneNumber phone1 = new PhoneNumber("+111111111");
//             PhoneNumber phone2 = new PhoneNumber("+222222222");
//             phone1.setPerson(person);
//             phone2.setPerson(person);
//             person.addPhoneNumber(phone1);
//             person.addPhoneNumber(phone2);
//             System.out.println("Caso valido con più numeri: ");
//             System.out.println(phone1);
//             System.out.println(phone2);
//             System.out.println(person);
//         } catch (IllegalArgumentException e) {
//             System.out.println("Errore (più numeri associati): " + e.getMessage());
//         }
//         try {
//             // Caso errore: Stesso numero associato a due persone diverse
//             Person person1 = new Person(true, "Salvatore", "D'Ambrosio", LocalDate.of(1996, 5, 10),
//                     "sd.io@hotmail.com");
//             Person person2 = new Person(false, "Maria", "Rossi", LocalDate.of(1995, 8, 20), "maria.rossi@example.com");

//             PhoneNumber sharedPhone = new PhoneNumber("+123456789");

//             sharedPhone.setPerson(person1);
//             person1.addPhoneNumber(sharedPhone);

//             sharedPhone.setPerson(person2);
//             person2.addPhoneNumber(sharedPhone);

//             System.out.println(sharedPhone);
//         } catch (IllegalArgumentException e) {
//             System.out.println("Errore (stesso numero per due persone): " + e.getMessage());
//         }
//     }

// }
