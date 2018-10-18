import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { HTTP } from 'app/utilities';
import { ClientAccountProvider } from 'app/providers'
import { ActivatedRoute, Params } from '@angular/router';

@Component({
    selector: 'app-client-account-loan-details',
    templateUrl: './client-account-loan-details.component.html',
    styleUrls: ['./client-account-loan-details.component.scss'],
    encapsulation: ViewEncapsulation.None
})
export class ClientAccountLoanDetailsComponent implements OnInit {
    public data = {
        clientAccount: "",
        letterOfOfferSignedDate: "",
        accountActivationDate: "",
        accountTerminationDate: "",
        status: "",
        availabilityPeriodMonths: "",
        facilityFee: "",
        accountMaturityDate: "",
        accountClosedDate: "",
        currencyisocode: "",
        productTypeDescription: "",
        interestRateSpread1: "",
        interestRateSpread2: "",
        rateType: "",
        interestRateSource1: "",
        interestRateSource2: "",
        loanAmount: "",
        loanTenure: "",
        repaymentType: "",
        instalmentFrequency: "",
        repaymentPeriodMonths: "",
        repaymentTypeToSpecify: "",
        instalmentType: "",
        dueDayOfTheMonth: "",
        cancellationFee: "",
        bankApprovedDateGiro: "",
        accountNumberGiro: "",
        deductionDay: "",
        bankGiro: "",
        currencyPaymentLimitIfApplicable: "",
        remarksGiro: "",
        prepaymentFee: "",
        prepaymentFromDrawdownMonths: "",
        facilityFeeOfLoanAmount: "",
        disbursementType: "",
        overdueInterestRateSpread: "",
        overdueInterestRateSource: "",
    };

    public dataFu = [];

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

    constructor(
        private act: ActivatedRoute,
        private pro: ClientAccountProvider,
    ) { }

    ngOnInit() {
        this.getLoanDetail();
    }

    public getLoanDetail() {
        document.getElementById('preloader').style.display = 'block';

        let id = 0;
        this.act.params.subscribe((params: Params) => {
            id = params["_id"];
        });

        let obj = {
            sfId: id,
            type: 'L'
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