import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { HTTP, Utils, Token } from 'app/utilities';
import { ClientAccountCustomerProvider, ClientAccountProvider } from 'app/providers';
import { Params, ActivatedRoute } from '@angular/router';

@Component({
    selector: 'app-client-account-factoring-details',
    templateUrl: './client-account-factoring-details.component.html',
    styleUrls: ['./client-account-factoring-details.component.scss'],
    encapsulation: ViewEncapsulation.None
})
export class ClientAccountFactoringDetailsComponent implements OnInit {
    public dataClientAccountCustomer = [];
    public clientName = "";
    public id: any;
    public total: number = 0;
    public pageSize = 10;
    public curentPage = 1;
    public pager: any = {};
    public pagedItems: any[];
    public dataFu = [];
    public data = {
        clientAccount: "",
        letterOfOfferSignedDate: null,
        accountActivationDate: null,
        accountClosedDate: null,
        status: "",
        availabilityPeriodMonths: 0,
        accountMaturityDate: null,
        accountTerminationDate: null,

        currencyisocode: "",
        investmentLimit: 0,
        advancedRatio: 0,
        productTypeDescription: "",
        fciName: "",
        reassignmentPeriod: 0,

        interestRateSpread1: 0,
        interestRateSpread2: 0,
        rateType: "",
        interestRateSource1: "",
        interestRateSource2: "",

        factoringChargeRate1stTier: 0,
        factoringChargeRate2ndTier: 0,
        factoringChargeRate3rdTier: 0,
        minFactoringChargePerMonth: 0,
        handlingChargePerInvoice: 0,
        tierUpToFactoredVolume1st: 0,
        tierUpToFactoredVolume2nd: 0,

        factoringChargeRateSpread: 0,
        addFacRateType: "",
        factoringChargeSource: "",
        overdueGracePeriodDays: 0,
        facilityFeeInvestmentLimit: 0,
        facilityFee: 0,
        autoRequest: false

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
            paymentModeFactoring: {
                title: 'Payment Mode',
                type: 'string',
                filter: false
            }
        }
    };

    public settings3 = {
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
            sNo: {
                title: 'S.No',
                type: 'string',
                filter: false,
            },
            clientAccount: {
                title: 'Client Account No.',
                type: 'string',
                filter: false
            },
            customer: {
                title: 'Customer',
                type: 'string',
                filter: false
            },
            activationDate: {
                title: 'Activation Date',
                type: 'date',
                valuePrepareFunction: (value) => { return Utils.format(value, 'dd-MMM-yyyy') },
                filter: false
            },
            status: {
                title: 'Status',
                type: 'string',
                filter: false
            }
        }
    };

    public isChecked = false;

    constructor(private act: ActivatedRoute,
        private pro: ClientAccountProvider) { }

    ngOnInit() {
        let user = Token.getUser();
        this.clientName = user.clientName;
        this.act.params.subscribe((params: Params) => {
            this.id = params["_id"];
        });

        this.getFactoringDetail();
        this.searchClick(1);
    }

    public searchClick(page: any) {
        this.search(page);
        this.curentPage = page;
    }

    public search(page: any) {
        document.getElementById('preloader').style.display = 'block';
        let x = {
            filter: {
                clientAccount: this.id
            },

            page: page,
            size: this.pageSize
        }

        this.pro.getClientAccountCustomer(x).subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.dataClientAccountCustomer = rsp.result.data;
                this.total = rsp.result.total;
                let i = 0;
                i = this.pageSize * (page - 1);
                if (this.dataClientAccountCustomer != null) {
                    this.dataClientAccountCustomer.forEach(element => {
                        i++;
                        element.sNo = i;
                    });
                    this.setPage(page);
                }
            }
        });

        setTimeout(function () {
            document.getElementById('preloader').style.display = 'none';
        }, 500);
    }

    public setPage(page: number) {
        this.pager = this.getPager(this.total, page, this.pageSize);
        this.pagedItems = this.dataClientAccountCustomer.slice(this.pager.startIndex, this.pager.endIndex + 1);
    }

    private getPager(totalItems: number, currentPage: number = 1, pageSize: number = 1) {
        let totalPages = Math.ceil(totalItems / pageSize);

        if (currentPage < 1) {
            currentPage = 1;
        } else if (currentPage > totalPages) {
            currentPage = totalPages;
        }

        let startPage: number, endPage: number;
        if (totalPages <= 10) {
            // less than 10 total pages so show all
            startPage = 1;
            endPage = totalPages;
        } else {
            // more than 10 total pages so calculate start and end pages
            if (currentPage <= 6) {
                startPage = 1;
                endPage = 10;
            } else if (currentPage + 4 >= totalPages) {
                startPage = totalPages - 9;
                endPage = totalPages;
            } else {
                startPage = currentPage - 5;
                endPage = currentPage + 4;
            }
        }
        // calculate start and end item indexes
        let startIndex = (currentPage - 1) * pageSize;
        let endIndex = Math.min(startIndex + pageSize - 1, totalItems - 1);

        // create an array of pages to ng-repeat in the pager control
        let pages = Array.from(Array((endPage + 1) - startPage).keys()).map(i => startPage + i);

        // return object with all pager properties required by the view
        return {
            totalItems: totalItems,
            currentPage: currentPage,
            pageSize: pageSize,
            totalPages: totalPages,
            startPage: startPage,
            endPage: endPage,
            startIndex: startIndex,
            endIndex: endIndex,
            pages: pages
        };
    }

    public getFactoringDetail() {
        document.getElementById('preloader').style.display = 'block';

        let obj = {
            sfId: this.id,
            type: 'F'
        }

        this.pro.getDetails(obj).subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                this.data = rsp.result;
                this.dataFu = rsp.result.fundings;
                this.isChecked = rsp.result.autoRequest;
            } else {
                let msg = rsp.message;
            }
        });

        setTimeout(function () {
            document.getElementById('preloader').style.display = 'none';
        }, 500);
    }

    public saveAutoReq() {
        document.getElementById('preloader').style.display = 'block';

        let obj = {
            sfId: this.id,
            autoRequest: this.isChecked
        }

        this.pro.update(obj).subscribe((rsp: any) => {
            if (rsp.status === HTTP.STATUS_SUCCESS) {
                //console.log("ok");
            } else {
                let msg = rsp.message;
            }
        });

        setTimeout(function () {
            document.getElementById('preloader').style.display = 'none';
        }, 500);
    }
}