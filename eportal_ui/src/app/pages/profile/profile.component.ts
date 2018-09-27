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

    @ViewChild('addBuyTokensModal') public addBuyTokensModal: ModalDirective;

    constructor(private pro: UserProvider) { }

    ngOnInit() {
        this.view();
    }

    public save(valid: boolean){
        if (!valid) {
            return;
        }

        this.pro.save(this.vm).subscribe((rsp: any)=>{
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                alert('Success');
            }
            else{
                alert('Fail');
            }
        })
    }

    private view() {
        this.pro.view().subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.vm = rsp.result;
                console.log(this.vm);
            }
        }, (err) => {
            console.log(err);
        });
    }
}