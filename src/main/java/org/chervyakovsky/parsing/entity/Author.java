package org.chervyakovsky.parsing.entity;


public class Author {
    private String firstName;
    private String lastName;

    public Author() {
    }

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Author author = (Author) o;
        return firstName.equals(author.firstName) &&
                lastName.equals(author.lastName);
    }

    @Override
    public int hashCode() {
        int result = firstName == null ? 0 : firstName.hashCode();
        result = lastName == null ? result : result + lastName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(":").append(firstName).append(" ").append(lastName); // FIXME
        return stringBuilder.toString();
    }
}
