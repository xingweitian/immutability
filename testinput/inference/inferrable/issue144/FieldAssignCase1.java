import qual.Assignable;
import qual.Immutable;
import qual.Readonly;
import qual.ReceiverDependantMutable;

@Immutable
public class FieldAssignCase1 {

    // 1) forbidden() method restricts "o" to be anything but not @ReceiverDependantMutable
    // 2) Adding @ReceiverDependantMutable manually caused inference to fail, which indicates
    // that implication constraint serialization logic is correct!
    @Assignable Object o;
    FieldAssignCase1(Object o) {
        // :: fixable-error: (assignment.type.incompatible)
        this.o = o;
    }

    void forbidden(@Readonly FieldAssignCase1 this) {
        // This is just the corner case to forbid an @Assignable field not to be reassigned
        // :: fixable-error: (illegal.field.write)
        this.o = new @ReceiverDependantMutable Object();
    }
}
