import { Directive, forwardRef, Attribute } from "@angular/core";
import { Validator, AbstractControl, NG_VALIDATORS } from "@angular/forms";

@Directive({
    selector: '[validateEqual][formControlName],[validateEqual][formControl],[validateEqual][ngModel]',
    providers: [
        { provide: NG_VALIDATORS, useExisting: forwardRef(() => EqualValidator), multi: true }
    ]
})

export class EqualValidator implements Validator {
    constructor(@Attribute('validateEqual') public validateEqual: string) { }

    public validate(c: AbstractControl): { [key: string]: any } {
        // Self value (e.g. retype password)
        let v = c.value;

        // Control value (e.g. password)
        let e = c.root.get(this.validateEqual);

        // Value not equal
        if (e && v !== e.value) return {
            validateEqual: false
        }
        return null;
    }
}