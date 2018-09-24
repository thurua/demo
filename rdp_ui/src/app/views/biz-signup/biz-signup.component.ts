import { Component, OnInit } from '@angular/core';
import { SalesforceProvider } from '../../providers/providers';
import { Title, Meta } from '@angular/platform-browser';

@Component({
    selector: 'app-biz-signup',
    templateUrl: './biz-signup.component.html',
    styleUrls: ['./biz-signup.component.css']
})

export class BizSignupComponent implements OnInit {
    public vm: any = {};

    constructor(private pro: SalesforceProvider,
        private tit: Title,
        private meta: Meta) {
    }

    ngOnInit() {
        this.tit.setTitle("Red Dot Power Singapore | Retail Electricity | Business");
        this.meta.updateTag({ name: "description", content: "Red Dot Power is the No. 1 independent electricity retailer. Pay less for your energy cost through savings on your electricity bill with cheaper electricity prices. We are a power company licensed by Singapore Energy Market Authority (EMA) to supply to contestable commercial and industrial consumers." });

        this.getFormData();
        document.getElementById("residentialTab").setAttribute("class", "");
        document.getElementById("welcomeDiv").focus();
    }

    ngAfterViewChecked() {
        document.getElementById("welcomeDiv").setAttribute("type", "hidden");
    }

    private getFormData() {
        this.pro.getFormData().subscribe((rsp: any) => {
            if (rsp.callstatus == "success") {
                this.vm = rsp.result;
            }
        });
    }

    public scrollElement() {
        document.getElementById("business").scrollIntoView();
    }
}