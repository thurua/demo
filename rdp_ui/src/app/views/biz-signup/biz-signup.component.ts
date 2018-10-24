import { Component, OnInit } from '@angular/core';
import { SalesforceProvider } from '../../providers/providers';
import { Title, Meta } from '@angular/platform-browser';
declare var grecaptcha: any;

@Component({
    selector: 'app-biz-signup',
    templateUrl: './biz-signup.component.html',
    styleUrls: ['./biz-signup.component.css']
})

export class BizSignupComponent implements OnInit {
    public vm: any = {};
    public captchaError: boolean = false;
    public siteKey: string = '';

    constructor(private pro: SalesforceProvider,
        private tit: Title,
        private meta: Meta) {
    }

    ngOnInit() {
        var rurl = window.location.href;
        if (rurl.indexOf("uat.reddotpower.com.sg") != -1 || rurl.indexOf("rdp-uat.herokuapp.com") != -1) {
            this.siteKey = "6LftAHIUAAAAAOGAJANTcBv7QiKTRNJG8vEnJfU1";
        } else if (rurl.indexOf("reddotpower.com.sg") != -1 || rurl.indexOf("reddotpower.herokuapp.com") != -1) {
            this.siteKey = "6LfV_HEUAAAAAF-riAePSZAe7zc1MYGNEH_oLgs_";
        }
        else if (rurl.indexOf("rdp-dev.herokuapp.com") != -1) {
            this.siteKey = "6LeT2nEUAAAAAL8Dzz5LdwFqUwq6W4CGo_m_VcH6";
        } else {
            this.siteKey = "6Lf6xnEUAAAAAD_JEOlb3zfayVHTlxAkCiVKCMNp";
        }

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

    public resolved(e) {
        const response = grecaptcha.getResponse();
        if (response.length === 0) {
            this.captchaError = true;
            return;
        }

        /*let x = (<HTMLInputElement[]><any>document.getElementsByName("captcha_settings"))[0];
        let y = x.value.replace("BizFormCaptchaKeys", response);
        x.value = y;*/

        var z = (<HTMLButtonElement><any>document.getElementById("btnSubmit"));
        z.disabled = false;
    }
}