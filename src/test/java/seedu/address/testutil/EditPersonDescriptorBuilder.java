package seedu.address.testutil;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.MrtStation;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.SecLevel;
import seedu.address.model.person.Student;
import seedu.address.model.tag.EnrolDate;
import seedu.address.model.tag.Subject;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditPersonDescriptorBuilder {

    private EditPersonDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        descriptor = new EditPersonDescriptor();
    }

    public EditPersonDescriptorBuilder(EditPersonDescriptor descriptor) {
        this.descriptor = new EditPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code student}'s details
     */
    public EditPersonDescriptorBuilder(Student student) {
        descriptor = new EditPersonDescriptor();
        descriptor.setName(student.getName());
        descriptor.setPhone(student.getPhone());
        descriptor.setEmail(student.getEmail());
        descriptor.setAddress(student.getAddress());
        descriptor.setGender(student.getGender());
        descriptor.setSecLevel(student.getSecLevel());
        descriptor.setNearestMrtStation(student.getNearestMrtStation());
        descriptor.setSubjects(student.getSubjects());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Sets the {@code Gender} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withGender(String gender) {
        descriptor.setGender(new Gender(gender));
        return this;
    }

    /**
     * Sets the {@code SecLevel} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withSecLevel(String secLevel) {
        descriptor.setSecLevel(new SecLevel(secLevel));
        return this;
    }

    /**
     * Sets the {@code MrtStation} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withNearestMrtStation(String nearestMrtStation) {
        descriptor.setNearestMrtStation(new MrtStation(nearestMrtStation));
        return this;
    }

    /**
     * Parses the {@code Set<String> tags} into a {@code Set<Subject>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withSubjects(Set<String> tags) {
        Set<Subject> subjectSet = tags.stream().map(Subject::new).collect(Collectors.toSet());
        descriptor.setSubjects(subjectSet);
        return this;
    }

    /**
     * Parses the {@code Set<String> tags} and {@code Set<String> dates} into a {@code Set<Subject>}
     * and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withSubjects(Set<String> tags, Collection<String> dates) {
        Iterator<String> tagIterator = tags.iterator();
        Iterator<String> dateIterator = dates.iterator();
        Set<Subject> subjectSet = new HashSet<>();
        while (tagIterator.hasNext() && dateIterator.hasNext()) {
            String nextTag = tagIterator.next();
            EnrolDate nextDate = new EnrolDate(dateIterator.next());
            subjectSet.add(new Subject(nextTag, nextDate));
        }
        descriptor.setSubjects(subjectSet);
        return this;
    }

    public EditPersonDescriptor build() {
        return descriptor;
    }
}
