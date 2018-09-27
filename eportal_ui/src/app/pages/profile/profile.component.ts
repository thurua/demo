import { Component, OnInit, ViewEncapsulation, ViewChild } from '@angular/core';
import { ModalDirective} from 'ngx-bootstrap';
import { UserProvider} from 'app/providers/user';
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
    public msgInfo: String = "";

    @ViewChild('changePasswordModal') public changePasswordModal: ModalDirective;
    @ViewChild('infoModal') public infoModal: ModalDirective;

    constructor(private pro: UserProvider) { }

    ngOnInit() {
        this.view();
        this.entity.oldPassword = "";
        this.entity.newPassword = "";
        this.entity.confirmPassword = "";
    }

    public save(valid: boolean){
        if (!valid) {
            return;
        }

        this.pro.save(this.vm).subscribe((rsp: any)=>{
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.msgInfo = "Profile updated successful!";
                this.infoModal.show();
            }
            else{
                this.msgInfo = "Profile updated fail!";
                this.infoModal.show();
            }
        }, (err) => {
            console.log(err);
        });
    }

    private view() {
        this.pro.view().subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.vm = rsp.result;
            }
        }, (err) => {
            console.log(err);
        });
    }

    public changPassword(valid: boolean){
        if (!valid) {
            return;
        }

        if(this.entity.newPassword != this.entity.confirmPassword){
            this.msgInfo = "New Password and Confirm Password are not same.";
        }

        let x = {
            newPassword: this.entity.newPassword,
            oldPassword: this.entity.oldPassword
        };

        this.pro.changePassword(x).subscribe((rsp: any)=>{
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.msgInfo = "Change password successfully!";
            }
            else{
                this.msgInfo = "Change password fail!";
            }
        }, (err) => {
            console.log(err);
        });

        this.changePasswordModal.hide();
        this.infoModal.show();
    }
}