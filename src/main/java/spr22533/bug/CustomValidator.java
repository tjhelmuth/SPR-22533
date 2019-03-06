package spr22533.bug;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CustomValidator implements ConstraintValidator<Buggy, Object> {
    private final RoleRepo roleRepo;

    @Autowired
    public CustomValidator(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public void initialize(Buggy constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        roleRepo.findAll();
        return true;
    }
}
