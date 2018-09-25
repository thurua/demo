import { Component, OnInit, ViewEncapsulation, AfterContentChecked, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Title, Meta } from '@angular/platform-browser';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { SalesforceProvider } from '../../providers/providers';
import { ModalDirective, TooltipModule } from 'ngx-bootstrap';
import { Utils } from '../../util';
declare var grecaptcha: any;

@Component({
    selector: 'app-res-promo',
    templateUrl: './res-promo.component.html',
    styleUrls: ['./res-promo.component.css',
        '../../../../node_modules/ngx-bootstrap/datepicker/bs-datepicker.css',
    ],
    encapsulation: ViewEncapsulation.None
})

export class ResPromoComponent implements OnInit, AfterContentChecked {
    vm: any = {
        "percentDiscount": '',
    };

    public vm1: any = {};
    public vmPackage: any = {};
    public planList: any[] = [];
    public bsValue = new Date();
    public showReadFactSheet = false;
    public showReadTerms = false;
    public showPlanName: string = '';
    public maxDate: Date;
    public minDate: Date;
    public minDatePreferred: Date;
    public maxDatePreferred: Date;
    public showValid_eQPlus: boolean = false;
    public loader: boolean = false;
    public isExist: boolean = false;
    public captchaError: boolean = false;
    public listCompany: any[] = [];
    public data: any[] = [];
    public isBlank: boolean = true;
    public planNameYR: any;
    public nameOfPricePlanMeter: String = '';
    public aMIMeterInstallationFee: String = '';
    public decode_planName: string;
    public existingMeterTypePop: string = '';
    public siteKey: string = '';
    public accountNumberTooltip: string = `
    <div>
      <img class="img-width" src="../assets/img/AccountNumber.jpg" />
    </div>
  `;
    public meterTypeTooltip: string = `
  <div>
      <img class="img-width" src="../assets/img/MeterType.png" />
    </div>
  `;
    public finalTypeTooltip: string = `
  <div class="div-border">
      <p>This refers to final meter reading before transferring out from SP Group. If you select No, SP Group will charge $20 for ad-hoc meter reading.</p>
    </div>
  `;
    public preferredDateOfSupplyTooltip: string = `
  <div class="div-border">
      <p>Earliest Preferred Date of Supply is 7 business days from Registration Date.</p>
    </div>
  `;

    @ViewChild('factSheetModal') public factSheetModal: ModalDirective;
    @ViewChild('termConditionsModal') public termConditionsModal: ModalDirective;
    @ViewChild('meterTypeModal') public meterTypeModal: ModalDirective;
    @ViewChild('requestForMeterChangeModal') public requestForMeterChangeModal: ModalDirective;
    @ViewChild('changeToAmiMeterModal') public changeToAmiMeterModal: ModalDirective;
    @ViewChild('checkExistModal') public checkExistModal: ModalDirective;

    constructor(private router: Router,
        private pro: SalesforceProvider,
        private utl: Utils,
        private act: ActivatedRoute,
        private tit: Title,
        private meta: Meta) { }

    ngOnInit() {
        var rurl = window.location.href;
        if (rurl.indexOf("reddotpower.com.sg") != -1 || rurl.indexOf("reddotpower.herokuapp.com") != -1) {
            this.siteKey = "6LfV_HEUAAAAAF-riAePSZAe7zc1MYGNEH_oLgs_";
        } else if (rurl.indexOf("uat.reddotpower.com.sg") != -1 || rurl.indexOf("rdp-uat.herokuapp.com") != -1) {
            this.siteKey = "6LftAHIUAAAAAOGAJANTcBv7QiKTRNJG8vEnJfU1";
        }
        else if (rurl.indexOf("rdp-dev.herokuapp.com") != -1) {
            this.siteKey = "6LeT2nEUAAAAAL8Dzz5LdwFqUwq6W4CGo_m_VcH6";
        }
        else {
            this.siteKey = "6Lf6xnEUAAAAAD_JEOlb3zfayVHTlxAkCiVKCMNp";
        }

        this.tit.setTitle("Red Dot Power | Electricity Retailer Singapore | Residential");
        this.meta.updateTag({ name: "description", content: "Red Dot Power is Singapore's No. 1 independent electricity retailer. Switch to us to enjoy best discount off your electricity bill with fixed rate, discount off tariff and peak and off-peak plan. A power company licensed by Singapore Energy Market Authority (EMA) to supply to residential, commercial and industrial." });

        this.checkPromoCode();
        this.getPackageData();

        this.maxDate = new Date();
        this.minDate = new Date();
        this.minDate.setDate(this.minDate.getDate() - 21);
        this.vm.registrationDate = new Date();

        let dateAfterWorking = this.utl.nextWorkingDay(this.vm.registrationDate, 7, false);
        this.minDatePreferred = this.vm.preferredDateOfSupply = dateAfterWorking;
        this.maxDatePreferred = this.utl.nextWorkingDay(this.vm.registrationDate, 90, true);

        document.getElementById("userName").focus();
        this.setDefaultSelectList();
    }

    public setDefaultSelectList() {
        this.vm.residentialType = '';
        this.vm.selfRead = '';
        this.vm.existingMeterType = '';
        this.vm.changeToAmiMeter = 'No';
    }

    ngAfterContentChecked() { }

    public newResPromo(valid: boolean, form: any) {
        if (this.isExist) {
            this.checkExistModal.show();
            return;
        }

        const response = grecaptcha.getResponse();

        if (response.length === 0) {
            this.captchaError = true;
            return;
        }

        if (!valid || !this.vm.agreedTermsConditions
            || !this.vm.agreedFactSheet
            || !this.vm.agreedAdvisoryNotice) {
            switch (false) {
                case form._directives[0].valid:
                    document.getElementById("userName").focus();
                    break;

                case form._directives[1].valid:
                    document.getElementById("userEmail").focus();
                    break;

                case form._directives[2].valid:
                    document.getElementById("contactNo").focus();
                    break;

                case form._directives[3].valid:
                    document.getElementById("telNo").focus();
                    break;

                case form._directives[4].valid:
                    document.getElementById("nricNo").focus();
                    break;

                case form._directives[5].valid:
                    document.getElementById("postalCode").focus();
                    break;

                case form._directives[6].valid:
                    document.getElementById("address1").focus();
                    break;

                case form._directives[8].valid:
                    document.getElementById("residentialType").focus();
                    break;

                case form._directives[9].valid:
                    document.getElementById("accountNumber").focus();
                    break;

                case form._directives[10].valid:
                    document.getElementById("planName").focus();
                    break;

                case form._directives[11].valid:
                    document.getElementById("duration").focus();
                    break;

                case form._directives[13].valid:
                    document.getElementById("selfRead").focus();
                    break;

                case form._directives[14].valid:
                    document.getElementById("preferredDateOfSupply").focus();
                    break;

                case form._directives[15].valid:
                    document.getElementById("existingMeterType").focus();
                    break;

                case form._directives[16].valid:
                    document.getElementById("changeToAmiMeter").focus();
                    break;
            }

            return;
        }

        let d = new Date();
        this.vm.createdDate = d.toISOString();
        this.vm.systemModStamp = d.toISOString();

        let obj: any = {
            "address1": this.vm.address1,
            "address2": this.vm.address2,
            "agreedFactSheet": this.vm.agreedFactSheet,
            "agreedTermsConditions": this.vm.agreedTermsConditions,
            "changeToAmiMeter": this.vm.changeToAmiMeter,
            "company": this.vm.userName,
            "createdDate": this.vm.createdDate,
            "currentMeterType": this.vm.existingMeterType,
            "duration": this.vm.duration,
            "ebsNumber": this.vm.accountNumber,
            "email": this.vm.userEmail,
            "isDeleted": false,
            "lastName": this.vm.userName,
            "leadSource": "Website",
            "masterRecordExternalId": null,
            "masterRecordId": "",
            "meterSelfRead": this.vm.selfRead,
            "mobilePhone": this.vm.contactNo,
            "name": this.vm.userName,
            "planName": this.vm.planName,
            "postalCode": this.vm.postalCode,
            "residentialType": this.vm.residentialType,
            "salesPerson": this.vm.salePersion ? this.vm.salePersion : "",
            "status": "New",
            "systemModStamp": this.vm.systemModStamp,
            "nightRate": this.vm.nightRate,
            "percentDiscount": this.vm.percentDiscount ? this.vm.percentDiscount : 0,
            "rate": this.vm.rate,
            "phone": this.vm.telNo,
            "nricNo": this.vm.nricNo,
            "type": "R",
            "isPromo": true,
            "reCaptcha": response
        };

        if (this.vm.preferredDateOfSupply != undefined) {
            obj.planStartDate = this.vm.preferredDateOfSupply.toISOString();
        }

        if (this.vm.registrationDate != undefined) {
            obj.registrationDate = this.vm.registrationDate.toISOString();
        }

        if (this.vm.contractEndDate != undefined) {
            obj.contractEndDateOem = this.vm.contractEndDate.toISOString();
        }

        // Invoking loading spinner
        this.loader = true;

        this.pro.saveLead(obj).subscribe((rsp: any) => {
            if (rsp.callstatus == "success") {
                // Encode
                let factSheet = btoa(rsp.result.factSheet);
                let salesAgreement = btoa(rsp.result.salesAgreement);
                let webReferenceNo = btoa(rsp.result.webReferenceNo);

                let param = '/promo/thankyou/' + factSheet + '/' + salesAgreement + '/' + webReferenceNo;
                this.router.navigate([param]);
            }
            else {
                if (rsp.message == "101") {
                    this.checkExistModal.show();
                } else {
                    alert("Fail, pls try again..." + rsp.message);
                }
            }

            this.loader = false;
        }, err => { alert("Error connection"); });
    }

    public updatePlanDetails() {
        // eQ+
        if (this.vm.planName == this.vmPackage.eQPlusSfid) {
            this.vm.duration = "24";
            this.showValid_eQPlus = false;
        } else {
            this.showValid_eQPlus = false;
        }

        if (this.vm.duration == undefined || this.vm.duration == '') {
            return;
        }

        let x = {
            "duration": this.vm.duration,
            "sfid": this.vm.planName,
            "isPromo": true
        }

        this.pro.searchPackage(x).subscribe((rsp: any) => {
            if (rsp.callstatus === "success") {
                let x = rsp.result;
                let planDetails = "";

                if (x.sfid === this.vmPackage.eSaveSfid) {
                    planDetails = x.percentDiscount + "% discount off regulated tariff.";
                    this.planNameYR = this.vm.duration / 12 + "YR";
                }
                if (x.sfid === this.vmPackage.eFixSfid) {
                    planDetails = "Electricity rate at " + x.rate + " cents/kWh.";
                    this.planNameYR = this.vm.duration / 12 + "YR";
                }
                if (x.sfid === this.vmPackage.eQPlusSfid) {
                    planDetails = "Electricity rate at " + x.rate + " cents/kWh (peak period), " + x.nightRateDollars
                        + " cents/kWh (off-peak period).";
                    this.planNameYR = "";
                }

                this.vm.planDetails = planDetails;
                this.showPlanName = x.name;
                this.vm.percentDiscount = x.percentDiscount;
                this.vm.rate = x.rate;
                this.vm.nightRate = x.nightRateDollars;
                let a = this.vm.existingMeterType;
                let b = this.vm.changeToAmiMeter;
                this.existingMeterTypePop = a;

                if (this.existingMeterTypePop != null || this.existingMeterTypePop != undefined) {
                    this.existingMeterTypePop = this.existingMeterTypePop.replace('_', ' / ');
                }

                if (a && b && a == 'Cumulative_SRLP' && b == 'No') {
                    this.nameOfPricePlanMeter = '-EXISTING METER';
                }
                else {
                    this.nameOfPricePlanMeter = '-SMART METER';
                }

                if (a && b && a == 'Cumulative_SRLP' && b == 'Yes') {
                    this.aMIMeterInstallationFee = 'A one-time installation fee of $40 will apply.';
                }
                else {
                    this.aMIMeterInstallationFee = 'Not Applicable';
                }

                this.vm1.percentDiscount = x.percentDiscountOem;
                this.vm1.rate = x.rateOem;
                this.vm1.nightRate = x.nightRateDollarsOem;
                this.vm1.packageDescription = x.packageDescription;
            }
        }, err => this.pro.handleError(err));

        this.updateContractEndDate(this.vm.preferredDateOfSupply);
    }

    public clickSupply(dp) {
        this.updateContractEndDate(dp._bsValue);
    }

    public clickRegistrationDate(dpr) {
        if (dpr._bsValue != null || dpr._bsValue != undefined) {
            this.vm.registrationDate = dpr._bsValue;
            let dateAfterWorking = this.utl.nextWorkingDay(this.vm.registrationDate, 7, false);
            this.minDatePreferred = this.vm.preferredDateOfSupply = dateAfterWorking;
            this.maxDatePreferred = this.utl.nextWorkingDay(this.vm.registrationDate, 90, true);
        }
    }

    public search() {
        let x = {
            "block": "", // 10
            "postcode": this.vm.postalCode, // 408600
            "streetName": "" // Eunos Road 8
        }

        if (this.vm.postalCode != "") {
            this.isBlank = false;
        }
        else {
            this.isBlank = true;
        }

        this.pro.sgLocate(x).subscribe((rsp: any) => {
            if (rsp.Postcodes) {
                if (rsp.Postcodes.length > 0) {
                    let item = rsp.Postcodes[0];
                    this.vm.address1 = item.BuildingNumber + ' ' + item.StreetName;
                    this.vm.address2 = item.BuildingName;
                    this.vm.postalCode = item.Postcode;
                }
                else {
                    this.vm.address1 = "";
                    this.vm.address2 = "";
                    this.vm.postalCode = "";
                }
            }
        }, err => this.pro.handleError(err));
    }

    public selectItem(item) {
        this.vm.address1 = item.BuildingNumber + ' ' + item.StreetName;
        this.vm.address2 = item.BuildingName;
        this.vm.postalCode = item.Postcode;

        this.isBlank = true;
    }

    public clickReadFactSheet() {
        if (this.vm.userName && this.showPlanName && this.vm.duration) {
            this.factSheetModal.show();
        }
    }

    public existingMeterTypeChange() {
        if (this.vm.existingMeterType == "Cumulative_SRLP" && this.vm.changeToAmiMeter == "Yes") {
            this.requestForMeterChangeModal.show();
        }
    }

    public closeModal(modal) {
        modal.hide();
    }

    public changeToAmiMeterChange() {
        if (this.vm.changeToAmiMeter == "Yes") {
            this.requestForMeterChangeModal.show();
        }
    }

    public clickReadTermsAndConditions() {
        if (this.vm.userName && this.showPlanName && this.vm.duration) {
            this.termConditionsModal.show();
        }
    }

    private getPackage() {
        this.pro.getPackage(true).subscribe((rsp: any) => {
            if (rsp.callstatus == "success") {
                this.planList = rsp.result.data;
            }
        });
    }

    public validLead() {
        let x = {
            "ebsNumber": this.vm.accountNumber,
            "nricNo": this.vm.nricNo
        }

        this.isExist = false;
        this.pro.validLead(x).subscribe((rsp: any) => {
            if (rsp.callstatus == "success") {
                if (rsp.result) {
                    this.isExist = true;
                    this.checkExistModal.show();
                }
            }
        }, err => this.pro.handleError(err));
    }

    private checkPromoCode() {
        this.act.params.subscribe((params: Params) => {
            let code = params["planName"];
            this.pro.checkPromoCode(code).subscribe((rsp: any) => {
                if (rsp.callstatus == "success") {
                    if (!rsp.result) {
                        this.router.navigate(['/']);
                    }
                }
                else {
                    console.log(rsp.message);
                }
            }, err => this.pro.handleError(err));
        });
    }

    private updateContractEndDate(frDate) {
        if (this.vm.duration == undefined
            || frDate == undefined) {
            return;
        }

        if (frDate != null) {
            let toDate = new Date(frDate);

            let months = toDate.getMonth() + parseInt(this.vm.duration);
            let tmp1 = new Date(toDate.setMonth(months));

            let days = tmp1.getDate() - 1
            let tmp2 = new Date(tmp1.setDate(days));

            this.vm.contractEndDate = tmp2;
        }
        else {
            this.vm.contractEndDate = null;
        }
    }

    private getPackageData() {
        this.pro.getPackageData(true).subscribe((rsp: any) => {
            if (rsp.callstatus == "success") {
                this.vmPackage = rsp.result;
                this.getPackage();
            }
        });
    }

    public checkFirstDegit($event) {
        let value = $event.target.value;
        let tmp = value.toString();

        if (value && value.toString().length > 0 && isNaN(Number(tmp))) {
            let res = '';
            for (let i = 0; i < tmp.length; i++) {
                if (Number(tmp[i]) >= 0 || tmp[i] == "-") {
                    res = res + tmp[i];
                }
            }
            this.vm.accountNumber = res;
        }
    }

    private test() {
        this.vm.userName = 'Test 001';
        this.vm.userEmail = 'abca@gmail.com';
        this.vm.contactNo = '88888888';
        this.vm.nricNo = 'S1234555A';
        this.vm.postalCode = '608532';
        this.vm.address1 = "xxx";
        this.vm.residentialType = 'HDB-1rm';
        this.vm.accountNumber = '8888888888';
        this.vm.planName = 'a010k000004VKMOAA4';
        this.vm.duration = '24';
        this.vm.preferredDateOfSupply = '04/04/2019';
        this.vm.existingMeterType = 'AMI';
        this.vm.changeToAmiMeter = 'No';
        this.vm.selfRead = 'No';
    }
}