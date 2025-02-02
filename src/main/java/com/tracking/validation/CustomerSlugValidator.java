package com.tracking.validation;

import com.tracking.model.TrackingRequest;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;

public class CustomerSlugValidator implements ConstraintValidator<ValidCustomerSlug, String> {

    @Override
    public void initialize(ValidCustomerSlug constraintAnnotation) {
        // No initialization needed
    }

    @Override
    public boolean isValid(String customerSlug, ConstraintValidatorContext context) {
        if (customerSlug == null || customerSlug.isEmpty()) {
            return true;
        }
        System.out.println("customerSlug:"+customerSlug);
        HibernateConstraintValidatorContext hibernateContext = context.unwrap(HibernateConstraintValidatorContext.class);
        // Now you can access the root bean or custom parameters if needed
        Object rootBean = hibernateContext.getConstraintValidatorPayload(TrackingRequest.class);
        System.out.println("rootBean:"+rootBean.toString());

        // Dynamically adding customer name to the error message
        //Object rootBean = (TrackingRequest) context.getCustomerName();
        //Object rootBean = context.getCustomerName();
        
        if (rootBean instanceof TrackingRequest requestDto) {  // Replace with your DTO class
         
            // Validation regex for slug-case
            //String slugPattern = "^[a-z0-9]+(?:-[a-z0-9]+)*$";
            //boolean isValid = customerSlug.matches(slugPattern);

            String expectedSlug = convertToSlug(requestDto.getCustomerName()); // Convert name to slug
            boolean isValid = expectedSlug.equals(customerSlug);

            if (!isValid) {
                // Dynamically adding customer name to the error message
                //TrackingRequest trackingRequest = (TrackingRequest) context.getRootBean();
                
                //String customerName = trackingRequest.getCustomerName();
                //String dynamicMessage = String.format("Invalid customer slug for '%s'. Must be in slug-case/kebab-case (e.g., 'redbox-logistics').", customerName);

                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("customer_slug should be '" + expectedSlug + "'")
                        .addConstraintViolation();
            }

            return isValid;
        }

        return false; // If no valid object is found
    }


    private String convertToSlug(String name) {
        return name.toLowerCase().replace(" ", "-").replaceAll("[^a-z0-9-]", "");  // Simple slug conversion
    }
}
