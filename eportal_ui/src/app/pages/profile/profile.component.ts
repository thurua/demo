import { Component, OnInit, ViewEncapsulation, ViewChild } from '@angular/core';
import { ModalDirective } from 'ngx-bootstrap';
import { UserProvider } from 'app/providers/user';
import { HTTP } from '../../utilities/utility';

@Component({
    selector: 'app-profile',
    templateUrl: './profile.component.html',
    styleUrls: ['./profile.component.scss'],
    encapsulation: ViewEncapsulation.None
})

export class ProfileComponent implements OnInit {
    public vm: any = {};
    public entity: any = {};
    public msgInfo: string = "";
    public isMatch: boolean = false;
    public msgErrSpecialChar: string = "Password must be 8 characters and include numbers, uppercase and lowercase. Letters and one special character";

    @ViewChild('changePasswordModal') public changePasswordModal: ModalDirective;
    @ViewChild('infoModal') public infoModal: ModalDirective;

    constructor(private pro: UserProvider) { }

    ngOnInit() {
        this.view();
        this.resetPasswordPopup();
    }

    public save(valid: boolean) {
        if (!valid) {
            return;
        }

        this.pro.updateProfile(this.vm).subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.msgInfo = "Profile updated successful!";
                this.infoModal.show();
            }
            else {
                this.msgInfo = "Profile updated fail!";
                this.infoModal.show();
            }
        }, (err) => {
            console.log(err);
        });
    }

    private view() {
        this.pro.read().subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.vm = rsp.result;
                console.log(this.vm);
            }
        }, (err) => {
            console.log(err);
        });
    }

    public changPassword(valid: boolean) {
        if (!valid) {
            return;
        }

        let x = {
            newPassword: this.entity.newPassword,
            oldPassword: this.entity.oldPassword
        };

        this.pro.updatePassword(x).subscribe((rsp: any) => {
            console.log(rsp);
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.msgInfo = "Change password successfully!";
            }
            else {
                if (rsp.message == 'Old password is incorrect') {
                    this.msgInfo = "Old password is incorrect";
                }
            }
        }, (err) => {
            console.log(err);
        });

        this.changePasswordModal.hide();
        this.infoModal.show();
        this.resetPasswordPopup();
    }

    public resetPasswordPopup() {
        this.entity.oldPassword = "";
        this.entity.newPassword = "";
        this.entity.confirmPassword = "";
    }

    public closeChangePasswordPopup() {
        this.resetPasswordPopup();
        this.changePasswordModal.hide();
    }
}