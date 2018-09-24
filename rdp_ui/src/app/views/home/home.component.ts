import { Component, OnInit, ViewChild } from '@angular/core';
import { ModalDirective, TooltipModule } from 'ngx-bootstrap';
import { NgForm } from '@angular/forms';
import { SalesforceProvider } from '../../providers/providers';
import { Title, Meta } from '@angular/platform-browser';

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css']
})

export class HomeComponent implements OnInit {
    @ViewChild("FindOutSavingModal") public FindOutSavingModal: ModalDirective;
    public vmPackage: any = {};
    public listPlan: any[] = [];
    public entity: any = {};
    public currentElectricityRate: number = 0.203;
    public typeSelect: string = 'Businness'; // or Residential
    public flagPCSavingESave = false;
    public flagPCSavingEFix = false;
    public flagPCSavingEQPlus = false;
    public isResidential: boolean = true;
    public eSave: string;
    public eQPlus: string;
    public eFix: string;
    public isShow: boolean = false;
    public heightDes = 0;
    public heightDiscount = 0;
    public heightSaving = 0;

    public vmESAVE: any = {
        "name": "eSAVE"
    };

    public vmEFIX: any = {
        "name": "eFIX"
    };

    public vmEQPlus: any = {
        "name": "eQ+"
    };

    public listQuestion: any = {
        "q1": "",
        "q2": "",
        "q3": ""
    };

    constructor(private pro: SalesforceProvider,
        private tit: Title,
        private meta: Meta) { }

    ngOnInit() {
        this.tit.setTitle("Red Dot Power | Electricity Retailer Singapore | Residential");
        this.meta.updateTag({ name: "description", content: "Red Dot Power is Singapore's No. 1 independent electricity retailer. Switch to us to enjoy best discount off your electricity bill with fixed rate, discount off tariff and peak and off-peak plan. A power company licensed by Singapore Energy Market Authority (EMA) to supply to residential, commercial and industrial." });

        this.getPackageData();
        document.getElementById("residentialTab").setAttribute("class", "highlight-header");
    }

    public calculateClicked(form: NgForm) {
        if (form.valid) {
            // Refresh when placing the new featured plan
            this.flagPCSavingEFix = false;
            this.flagPCSavingESave = false;
            this.flagPCSavingEQPlus = false;

            if (this.vmESAVE != undefined) {
                this.vmESAVE = this.calcalateObject(this.vmESAVE, this.vmESAVE.rateOem, this.vmESAVE.nightRateDollarsOem,
                    this.vmESAVE.percentDiscountOem, this.entity.usage1, this.entity.usage2, this.entity.usage3);
            }
            if (this.vmEFIX != undefined) {
                this.vmEFIX = this.calcalateObject(this.vmEFIX, this.vmEFIX.rateOem, this.vmEFIX.nightRateDollarsOem,
                    this.vmEFIX.percentDiscountOem, this.entity.usage1, this.entity.usage2, this.entity.usage3);
            }
            if (this.vmEQPlus != undefined) {
                this.vmEQPlus = this.calcalateObject(this.vmEQPlus, this.vmEQPlus.rateOem, this.vmEQPlus.nightRateDollarsOem,
                    this.vmEQPlus.percentDiscountOem, this.entity.usage1, this.entity.usage2, this.entity.usage3);
            }

            let maxPCSaving = this.vmESAVE.percentSaving;
            this.flagPCSavingESave = true;
            if (this.vmEFIX.percentSaving > maxPCSaving) {
                maxPCSaving = this.vmEFIX.percentSaving;
                this.flagPCSavingEFix = true;
                this.flagPCSavingESave = false;
                document.getElementById("eFixDiv").focus();
            }
            if (this.vmEQPlus != undefined && this.vmEQPlus.percentSaving > maxPCSaving) {
                maxPCSaving = this.vmEFIX.percentSaving;
                this.flagPCSavingEFix = false;
                this.flagPCSavingESave = false;
                this.flagPCSavingEQPlus = true;
                document.getElementById("eQPlusDiv").focus();
            }
            this.FindOutSavingModal.hide();
            this.isShow = true;
            form.resetForm();
        }
        setTimeout(function () {
            let eSaveHeightSaving = document.getElementById("eSaveHeightSaving").offsetHeight;
            let eQPlusHeightSaving = document.getElementById("eQPlusHeightSaving").offsetHeight;
            let eFixHeightSaving = document.getElementById("eFixHeightSaving").offsetHeight;
            this.heightSaving = Math.max(eSaveHeightSaving, eFixHeightSaving, eQPlusHeightSaving);

            document.getElementById("eSaveHeightSaving").style.height = this.heightSaving + "px";
            document.getElementById("eFixHeightSaving").style.height = this.heightSaving + "px";
            document.getElementById("eQPlusHeightSaving").style.height = this.heightSaving + "px";
        }, 500);
    }

    private calcalateObject(vm: any,
        rate: number, nightRate: number,
        percentDiscount: number,
        usage1: number, usage2: number, usage3: number) {
        //newPlan =B2*(100-E2)%
        let newPlan = vm.currentElectricityRate * (100 - percentDiscount) / 100;
        let total = usage1 + usage2 + usage3;
        let avg = total / 3;

        //existingCode =B2*AVERAGE(F2,G2,H2)*12
        // [st 4/4/2018] - currentElectricityRate needs to divide by 100, because the value is in cents
        let existingCost = (vm.currentElectricityRate / 100) * avg * 12;

        if (vm.sfid == this.vmPackage.eFixSfid) {
            newPlan = rate;
        }
        if (vm.sfid == this.vmPackage.eQPlusSfid) {
            newPlan = 0;
        }

        //NewPlanCost =I2*AVERAGE(F2,G2,H2)*12
        let newPlanCost = newPlan * avg * 12;
        //=SUM(C4*(AVERAGE(F4,G4,H4)*36%)*12,D4*(AVERAGE(F4,G4,H4)*64%)*12)
        if (vm.sfid == this.vmPackage.eQPlusSfid) {
            let a1 = rate * avg * (36 / 100) * 12;
            let a2 = nightRate * avg * (64 / 100) * 12;
            newPlanCost = a1 + a2;
        }
        //saving =J2-K2
        // [st 4/4/2018] - newPlanCost needs to divide by 100, because the value is in cents
        let saving = existingCost - (newPlanCost / 100);
        //%Saving =L2/J2*100
        let percentSaving = saving / existingCost * 100;

        let obj: any = {};
        obj.existingCost = existingCost;
        obj.newPlanCost = newPlanCost;
        obj.saving = saving.toFixed(0);
        obj.percentSaving = percentSaving.toFixed(0);
        obj.name = vm.name;
        obj.packageDescription = vm.packageDescription;

        obj.nightRateDollars = vm.nightRateDollarsOem;
        obj.nightRateDollarsOem = vm.nightRateDollarsOem;
        obj.packageDescription = vm.packageDescription;
        obj.percentDiscount = vm.percentDiscount;
        obj.percentDiscountOem = vm.percentDiscountOem;
        obj.publishToWeb = vm.publishToWeb;
        obj.rate = vm.rate;
        obj.rateOem = vm.rateOem;
        obj.sfid = vm.sfid;
        obj.currentElectricityRate = vm.currentElectricityRate;

        return obj;
    }

    private getPackage() {
        this.pro.getPackage().subscribe((rsp: any) => {
            if (rsp.callstatus === "success") {
                this.listPlan = rsp.result.data;
                this.getData();
                setTimeout(function () {
                    let eSaveHeightDiscount = document.getElementById("eSaveHeightDiscount").offsetHeight;
                    let eSaveHeightDes = document.getElementById("eSaveHeightDes").offsetHeight;
                    let eFixHeightDiscount = document.getElementById("eFixHeightDiscount").offsetHeight;
                    let eFixHeightDes = document.getElementById("eFixHeightDes").offsetHeight;
                    let eQPlusHeightDiscount = document.getElementById("eQPlusHeightDiscount").offsetHeight;
                    let eQPlusHeightDes = document.getElementById("eQPlusHeightDes").offsetHeight;

                    this.heightDiscount = Math.max(eSaveHeightDiscount, eFixHeightDiscount, eQPlusHeightDiscount);
                    this.heightDes = Math.max(eSaveHeightDes, eFixHeightDes, eQPlusHeightDes);

                    document.getElementById("eSaveHeightDiscount").style.height = this.heightDiscount + "px";
                    document.getElementById("eSaveHeightDes").style.height = this.heightDes + "px";
                    document.getElementById("eFixHeightDiscount").style.height = this.heightDiscount + "px";
                    document.getElementById("eFixHeightDes").style.height = this.heightDes + "px";
                    document.getElementById("eQPlusHeightDiscount").style.height = this.heightDiscount + "px";
                    document.getElementById("eQPlusHeightDes").style.height = this.heightDes + "px";
                }, 500);
            }
        }, err => this.pro.handleError(err));
    }

    private getData() {
        let x = this.listPlan.find(x => x.sfid === this.vmPackage.eSaveSfid);
        this.vmESAVE = x;
        if (x != undefined) {
            this.vmESAVE = {
                name: x.name,
                nightRateDollars: x.nightRateDollarsOem ? x.nightRateDollarsOem : 0,
                nightRateDollarsOem: x.nightRateDollarsOem ? x.nightRateDollarsOem : 0,
                packageDescription: x.packageDescription,
                percentDiscount: x.percentDiscount ? x.percentDiscount : 0,
                percentDiscountOem: x.percentDiscountOem ? x.percentDiscountOem : 0,
                publishToWeb: x.publishToWeb,
                rate: x.rate ? x.rate : 0,
                rateOem: x.rateOem ? x.rateOem : 0,
                sfid: x.sfid,
                currentElectricityRate: x.currentElectricityRate
            }
        }

        x = this.listPlan.find(x => x.sfid === this.vmPackage.eFixSfid);
        this.vmEFIX = x;
        if (x != undefined) {
            this.vmEFIX = {
                name: x.name,
                nightRateDollars: x.nightRateDollarsOem ? x.nightRateDollarsOem : 0,
                nightRateDollarsOem: x.nightRateDollarsOem ? x.nightRateDollarsOem : 0,
                packageDescription: x.packageDescription,
                percentDiscount: x.percentDiscount ? x.percentDiscount : 0,
                percentDiscountOem: x.percentDiscountOem ? x.percentDiscountOem : 0,
                publishToWeb: x.publishToWeb,
                rate: x.rate ? x.rate : 0,
                rateOem: x.rateOem ? x.rateOem : 0,
                sfid: x.sfid,
                currentElectricityRate: x.currentElectricityRate
            }

            x = this.listPlan.find(x => x.sfid === this.vmPackage.eQPlusSfid);
            this.vmEQPlus = x;
            if (x != undefined) {
                this.vmEQPlus = {
                    name: x.name,
                    nightRateDollars: x.nightRateDollarsOem ? x.nightRateDollarsOem : 0,
                    nightRateDollarsOem: x.nightRateDollarsOem ? x.nightRateDollarsOem : 0,
                    packageDescription: x.packageDescription,
                    percentDiscount: x.percentDiscount ? x.percentDiscount : 0,
                    percentDiscountOem: x.percentDiscountOem ? x.percentDiscountOem : 0,
                    publishToWeb: x.publishToWeb,
                    rate: x.rate ? x.rate : 0,
                    rateOem: x.rateOem ? x.rateOem : 0,
                    sfid: x.sfid,
                    currentElectricityRate: x.currentElectricityRate
                }
            }
        }
    }

    public calHelpMeRecommend() {
        let tmp = this.listQuestion;
        let a = "a";
        let b = "b";
        if (tmp) {
            // Refresh when placing the new featured plan
            this.flagPCSavingEFix = false;
            this.flagPCSavingESave = false;
            this.flagPCSavingEQPlus = false;

            if (tmp.q1 == a) {
                // eQ+
                this.flagPCSavingEQPlus = true;
                this.flagPCSavingEFix = false;
                this.flagPCSavingESave = false;

                document.getElementById("eQPlusDiv").focus();
            }
            else if ((tmp.q2 == a && tmp.q3 == a) || (tmp.q2 == b && tmp.q3 == a)) {
                // eFIX
                this.flagPCSavingEQPlus = false;
                this.flagPCSavingEFix = true;
                this.flagPCSavingESave = false;

                document.getElementById("eFixDiv").focus();
            }
            else if ((tmp.q2 == b && tmp.q3 == b) || (tmp.q2 == a && tmp.q3 == b)) {
                // eSAVE
                this.flagPCSavingEQPlus = false;
                this.flagPCSavingEFix = false;
                this.flagPCSavingESave = true;

                document.getElementById("eSaveDiv").focus();
            }
        }
    }

    public helpMeRecommendClicked() {
        this.listQuestion = {
            "q1": "",
            "q2": "",
            "q3": ""
        };
    }

    private getPackageData() {
        this.pro.getPackageData().subscribe((rsp: any) => {
            if (rsp.callstatus == "success") {
                this.vmPackage = rsp.result;
                this.getPackage();
            }
        });
    }
}