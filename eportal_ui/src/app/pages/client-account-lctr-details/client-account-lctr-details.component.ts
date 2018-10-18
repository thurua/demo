import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { HTTP } from 'app/utilities';
import { ClientAccountProvider } from 'app/providers'
import { ActivatedRoute, Params } from '@angular/router';

@Component({
    selector: 'app-client-account-details',
    templateUrl: './client-account-lctr-details.component.html',
    styleUrls: ['./client-account-lctr-details.component.scss'],
    encapsulation: ViewEncapsulation.None
})
export class ClientAccountLctrDetailsComponent implements OnInit {
    public dataReason = [];
    public data = {
        clientAccount: "",
        letterOfOfferSignedDate: null,
        accountActivationDate: null,
        accountTerminationDate: null,
        status: "",
        availabilityPeriodMonths: null,
        accountMaturityDate: null,
        accountClosedDate: null,
        currencyisocode: "",
        productTypeDescription: "",
        interestRateSpread1: null,
        interestRateSpread2: null,
        rateType: null,
        interestRateSource1: null,
        interestRateSource2: null,
        facilityFee: null,
        loanAmount: null,
        loanTenure: null,
        trPeriod: null,
        serviceCharge: null,
        additionalServiceChargePerMonth: null,
        extensionFeeFlat: null,
        extensionFeePerMonth: null,
        usanceAcceptanceChargePerMonth: null,
        lcValidityPeriod: null,
        serviceChargeMin: null,
        additionalServiceChargeMin: null,
        extensionFeeFlatMin: null,
        extensionFeePerMonthMin: null,
        usanceAcceptanceChargeMin: null,
        facilityFeeOfLoanAmount: null,
        annualRenewalFee: null,
        bankApprovedDateGiro: null,
        accountNumberGiro: "",
        deductionDay: null,
        bankGiro: "",
        currencyPaymentLimitIfApplicable: "",
        remarksGiro: "",
    }

    public dataFu = [];

    constructor(
        private act: ActivatedRoute,
        private pro: ClientAccountProvider,
    ) { }

    ngOnInit() {
        this.getLctrDetail();
    }

    public settings = {
        selectMode: 'single',  //single|multi
        hideHeader: false,
        hideSubHeader: false,
        actions: {
            add: false,
            edit: false,
            delete: false,
            custom: [],
        },
        handle: {
            editable: false
        },
        noDataMessage: 'No data found',
        columns: {
            primaryFunding: {
                title: 'Primary',
                type: 'string',
                filter: false,
            },
            currencyisocodeF: {
                title: 'Currency',
                type: 'string',
                filter: false
            },
            bankFunding: {
                title: 'Bank',
                type: 'string',
                filter: false
            },
            accountNumberF: {
                title: 'Account Number',
                type: 'string',
                filter: false
            },
            paymentModeLLctr: {
                title: 'Payment Mode',
                type: 'string',
                filter: false
            }
        }
    };

    public getLctrDetail() {
        document.getElementById('preloader').style.display = 'block';

        let id = 0;
        this.act.params.subscribe((params: Params) => {
            id = params["_id"];
        });

        let obj = {
            sfId: id,
            type: 'LC'
        }

        this.pro.getDetails(obj).subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.data = rsp.result;
                this.dataFu = rsp.result.fundings;
            } else {
                let msg = rsp.message;
            }
        });

        setTimeout(function () {
            document.getElementById('preloader').style.display = 'none';
        }, 500);
    }
}
