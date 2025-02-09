// package com.group.myCalendar.model;

// import java.time.Period;
// import java.time.LocalDate;

// import java.util.*;

// import jakarta.persistence.*;
// import jakarta.validation.constraints.*;

// @SuppressWarnings("unused")
// @Entity
// @Table(name = "person")
// public class Person {
//     @Id
//     @SequenceGenerator(name = "person_sequence", sequenceName = "person_sequence", allocationSize = 1)
//     @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_sequence")
//     private Long id;

//     @NotNull(message = "sex cannot be null")
//     @Column(nullable = false)
//     private Boolean isMale;

//     @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ '-]+$", message = "Name contains invalid characters")
//     @NotNull(message = "name cannot be null")
//     @Column(nullable = false)
//     private String name;

//     @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ '-]+$", message = "Surname contains invalid characters")
//     @NotNull(message = "surname cannot be null")
//     @Column(nullable = false)
//     private String surname;

//     @NotNull(message = "Date of birth cannot be null")
//     @Past(message = "Date of birth must be in the past")
//     @Column(nullable = false)
//     private LocalDate dateOfBirth;

//     @Transient
//     private Integer age;

//     @NotNull(message = "email cannot be null")
//     @Column(nullable = false, unique = true)
//     @Email
//     private String email;

//     @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
//     private Set<PhoneNumber> phoneNumbers;

//     // CONSTRUCTORS
//     public Person() {
//     }

//     public Person(Boolean isMale, String name, String surname, LocalDate dateOfBirth, String email) {
//         // CAPIRE SE SI POSSONO SEMPRE USARE I SETTER E NON PERFORZA GLI ASSEGNAMENTI
//         // DIRETTI
//         // "this.name = name;", ecc
//         setIsMale(isMale);
//         setName(name);
//         setSurname(surname);
//         setDateOfBirth(dateOfBirth);
//         setEmail(email);
//         setPhoneNumbers(phoneNumbers);
//     }

//     public Person(Boolean isMale, String name, String surname, LocalDate dateOfBirth, String email,
//             HashSet<PhoneNumber> phoneNumbers) {
//         this(isMale, name, surname, dateOfBirth, email);
//         this.phoneNumbers = phoneNumbers;
//     }

//     // Test only
//     public Person(Long id, Boolean sex, String name, String surname, LocalDate dateOfBirth, String email,
//             HashSet<PhoneNumber> phoneNumbers) {
//         this(sex, name, surname, dateOfBirth, email, phoneNumbers);
//         this.id = id;
//     }

//     // GETTERS
//     public Long getId() {
//         return id;
//     }

//     public Boolean getIsMale() {
//         return isMale;
//     }

//     public String getName() {
//         return name;
//     }

//     public String getSurname() {
//         return surname;
//     }

//     public LocalDate getDateOfBirth() {
//         return dateOfBirth;
//     }

//     public Integer getAge() {
//         return Period.between(this.dateOfBirth, LocalDate.now()).getYears();
//     }

//     public String getEmail() {
//         return email;
//     }

//     public Set<PhoneNumber> getPhoneNumbers() {
//         return phoneNumbers;
//     }

//     // SETTERS
//     public void setId(Long id) {
//         this.id = id;
//     }

//     public void setIsMale(Boolean isMale) {
//         if (isMale == null) {
//             throw new IllegalArgumentException("Gender cannot be null");
//         }
//         this.isMale = isMale;
//     }

//     public void setName(String name) {
//         if (name == null || name.trim().isEmpty()) {
//             throw new IllegalArgumentException("Name cannot be null, empty, or only spaces");
//         }
//         if (!name.matches("^[A-Za-zÀ-ÖØ-öø-ÿ '-]+$")) {
//             throw new IllegalArgumentException("Name cannot contain numbers or invalid characters");
//         }
//         this.name = name;
//     }

//     public void setSurname(String surname) {
//         if (surname == null || surname.trim().isEmpty()) {
//             throw new IllegalArgumentException("Surname cannot be null, empty, or only spaces");
//         }
//         if (!surname.matches("^[A-Za-zÀ-ÖØ-öø-ÿ '-]+$")) {
//             throw new IllegalArgumentException("Surname cannot contain numbers or invalid characters");
//         }
//         this.surname = surname;
//     }

//     public void setDateOfBirth(LocalDate dateOfBirth) {
//         if (dateOfBirth == null) {
//             throw new IllegalArgumentException("Date of birth cannot be null");
//         }
//         if (dateOfBirth.isAfter(LocalDate.now())) {
//             throw new IllegalArgumentException("Date of birth cannot be in the future");
//         }
//         this.dateOfBirth = dateOfBirth;
//     }

//     private void setAge(Integer age) {
//         this.age = age;
//     }

//     public void setEmail(String email) {
//         if (email == null || email.isEmpty()) {
//             throw new IllegalArgumentException("Email cannot be null or empty");
//         }
//         if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
//             throw new IllegalArgumentException("Invalid email format: " + email);
//         }
//         this.email = email;
//     }

//     public void setPhoneNumbers(Set<PhoneNumber> phoneNumbers) {
//         this.phoneNumbers = phoneNumbers;
//     }

//     // OTHERS
//     public void addPhoneNumber(PhoneNumber phoneNumber) {
//         if (phoneNumbers.contains(phoneNumber)) {
//             throw new IllegalArgumentException("Phone number is already registered to this person");
//         }
//         phoneNumbers.add(phoneNumber);
//         phoneNumber.setPerson(this);
//     }

//     public void removePhoneNumber(PhoneNumber phoneNumber) {
//         if (!phoneNumbers.contains(phoneNumber)) {
//             throw new IllegalArgumentException("Phone number is not associated with this person");
//         }
//         phoneNumbers.remove(phoneNumber);
//         phoneNumber.setPerson(null);
//     }

//     @Override
//     public String toString() {
//         return "Person{" +
//                 "id=" + id +
//                 ", sex=" + (isMale ? "Maschio" : "Femmina") +
//                 ", name=" + name +
//                 ", surname=" + surname +
//                 ", dateOfBirth=" + dateOfBirth +
//                 ", age=" + age +
//                 ", email=" + email +
//                 ", phoneNumbers=" + phoneNumbers.stream()
//                         .map(PhoneNumber::getNumber) // .map(phone -> phone.getNumber())
//                         .toList()
//                 +
//                 '}';
//     }

//     public static void main(String[] args) {
//         try {
//             // Caso valido: Creazione di una persona con un numero di telefono
//             PhoneNumber phone1 = new PhoneNumber("+123456789");
//             Person person1 = new Person(true, "Salvatore", "D'Ambrosio", LocalDate.of(1996, 5, 10),
//                     "sd.io@hotmail.com");
//             person1.addPhoneNumber(phone1);
//             System.out.println("Caso valido: " + person1);
//         } catch (IllegalArgumentException e) {
//             System.out.println("Errore (caso valido): " + e.getMessage());
//         }

//         try {
//             // Caso errore: Nome non valido
//             Person person2 = new Person(true, "", "D'Ambrosio", LocalDate.of(1996, 5, 10), "sd.io@hotmail.com");
//             System.out.println(person2);
//         } catch (IllegalArgumentException | NullPointerException e) {
//             System.out.println("Errore (nome non valido): " + e.getMessage());
//         }

//         try {
//             // Caso errore: Cognome non valido
//             Person person3 = new Person(true, "Salvatore", "dam123", LocalDate.of(1996, 5, 10), "sd.io@hotmail.com");
//             System.out.println(person3);
//         } catch (IllegalArgumentException | NullPointerException e) {
//             System.out.println("Errore (cognome non valido): " + e.getMessage());
//         }

//         try {
//             // Caso errore: Email non valida
//             Person person4 = new Person(true, "Salvatore", "D'Ambrosio", LocalDate.of(1996, 5, 10), "email-non-valida");
//             System.out.println(person4);
//         } catch (IllegalArgumentException e) {
//             System.out.println("Errore (email non valida): " + e.getMessage());
//         }

//         try {
//             // Caso errore: Data di nascita nel futuro
//             Person person5 = new Person(true, "Salvatore", "D'Ambrosio", LocalDate.of(2090, 5, 10),
//                     "sd.io@hotmail.com");
//             System.out.println(person5);
//         } catch (IllegalArgumentException e) {
//             System.out.println("Errore (data di nascita non valida): " + e.getMessage());
//         }

//         try {
//             // Caso errore: Duplicazione di numeri di telefono
//             PhoneNumber phone2 = new PhoneNumber("+123456789");
//             Person person6 = new Person(true, "Salvatore", "D'Ambrosio", LocalDate.of(1996, 5, 10),
//                     "sd.io@hotmail.com");
//             person6.addPhoneNumber(phone2);
//             System.out.println(person6);
//             person6.addPhoneNumber(phone2);
//             System.out.println(person6);
//         } catch (IllegalArgumentException e) {
//             System.out.println("Errore (numero duplicato): " + e.getMessage());
//         }

//         try {
//             // Caso valido: Persona con più numeri di telefono
//             PhoneNumber phone3 = new PhoneNumber("+111111111");
//             PhoneNumber phone4 = new PhoneNumber("+222222222");
//             Person person7 = new Person(false, "Maria", "Rossi", LocalDate.of(1995, 8, 20), "maria.rossi@example.com");
//             person7.addPhoneNumber(phone3);
//             person7.addPhoneNumber(phone4);
//             System.out.println(person7);

//         } catch (IllegalArgumentException e) {
//             System.out.println("Errore (più numeri): " + e.getMessage());
//         }

//         try {
//             // Caso errore: Rimozione di un numero non associato
//             PhoneNumber phone5 = new PhoneNumber("+333333333");
//             Person person8 = new Person(false, "Marco", "Bianchi", LocalDate.of(1994, 4, 15),
//                     "marco.bianchi@example.com");
//             person8.removePhoneNumber(phone5);
//             System.out.println(person8);
//         } catch (IllegalArgumentException e) {
//             System.out.println("Errore (rimozione non associato): " + e.getMessage());
//         }
//     }

// }
