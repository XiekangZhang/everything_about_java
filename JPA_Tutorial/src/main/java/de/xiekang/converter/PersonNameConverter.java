package de.xiekang.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class PersonNameConverter implements AttributeConverter<PersonName, String> {
    private static final String SEPARATOR = ", ";

    @Override
    public String convertToDatabaseColumn(PersonName personName) {
        if (personName == null)
            return null;

        StringBuilder stringBuilder = new StringBuilder();
        if (personName.getSurname() != null && !personName.getSurname().isEmpty()) {
            stringBuilder.append(personName.getSurname());
            stringBuilder.append(SEPARATOR);
        }

        if (personName.getName() != null && !personName.getName().isEmpty()) {
            stringBuilder.append(personName.getName());
        }
        return stringBuilder.toString();
    }

    @Override
    public PersonName convertToEntityAttribute(String s) {
        if (s == null || s.isEmpty())
            return null;
        String[] pieces = s.split(SEPARATOR);
        if (s == null || s.isEmpty())
            return null;
        PersonName personName = new PersonName();
        String firstPiece = !pieces[0].isEmpty() ? pieces[0] : null;
        if (s.contains(SEPARATOR)) {
            personName.setSurname(firstPiece);
            if (pieces.length >= 2 && pieces[1] != null && !pieces[1].isEmpty()) {
                personName.setName(pieces[1]);
            }
        } else {
            personName.setName(firstPiece);
        }
        return personName;
    }
}
