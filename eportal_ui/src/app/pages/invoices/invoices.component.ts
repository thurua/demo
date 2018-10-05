import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { HTTP, Utils } from '../../utilities/utility';
import { ScheduleProvider } from '../../providers/schedule';


@Component({
  selector: 'app-invoices',
  templateUrl: './invoices.component.html',
  styleUrls: ['./invoices.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class InvoicesComponent implements OnInit {
  public portalStatus: string = "";
  public customer: string = "";
  public scheduleNo = "";
  public clientId: string = "";
  public clientName: string = "";
  public invoiceNo = "";
  public lstCustomer: any[] = [];
  public lstStatus: any[] = [];
  public lstCA: any[] = [];
  public data = [];
  public clientAccountId = [];
  public customerId = [];
  public total: number = 0;
  public fromDate = new Date();
  public toDate = new Date();
  public minDate = new Date();
  public maxDate = new Date();

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
      creditNoteNo: {
        title: 'Invoice No.',
        filter: false,
        type: 'html',
        valuePrepareFunction: (cell, row) => {

          return `<a href="/#/pages/schedule-details/${row.id}">${row.scheduleNo}</a>`
        },
      },
      scheduleNo: {
        title: 'Schedule No.',
        type: 'string',
        filter: false
      },
      clientAccountNo: {
        title: 'Client Account No.',
        type: 'string',
        filter: false
      },
      documentType: {
        title: 'Document Type',
        type: 'string',
        filter: false
      },
      customer: {
        title: 'Customer / Supplier',
        type: 'string',
        filter: false
      },
      invoiceDate: {
        title: 'Invoice Date',
        type: 'date',
        filter: false
      },
      invoiceAmount: {
        title: 'Invoice Amount',
        type: 'string',
        filter: false
      },
      status: {
        title: 'Status',
        type: 'string',
        filter: false
      },
      createdBy: {
        title: 'Created By',
        type: 'string',
        filter: false
      },
      createdDateTime: {
        title: 'Created Date / Time',
        type: 'date',
        filter: false
      }
    }
  };

  constructor(
    private pro: ScheduleProvider,
    private utl: Utils) {
  }

  ngOnInit() {
    this.fromDate = this.utl.addMonths(this.fromDate, -6);
    this.minDate = this.utl.addMonths(this.minDate, -12);
    this.maxDate = this.utl.addMonths(this.maxDate, 12);
    this.toDate = this.utl.addMonths(this.toDate, -6);

    let user = JSON.parse(localStorage.getItem("CURRENT_TOKEN"));
    this.clientId = user.clientId;
    this.clientName = user.clientName;
    this.searchCA();

    let tmpStatus = {
      data: [{
        code: "",
        value: "-- Please Select --"
      }, {
        code: "1",
        value: "Accepted"
      }, {
        code: "2",
        value: "Rejected"
      }, {
        code: "3",
        value: "Unfunded"
      }, {
        code: "4",
        value: "Disputed"
      }, {
        code: "5",
        value: "Reassigned"
      }, {
        code: "6",
        value: "Reversed"
      }]
    }
    this.lstStatus = tmpStatus.data;

    let tmpCustomer = {
      dataCustomer: [{
        code: "",
        value: "-- Please Select --"
      }, {
        code: "1",
        value: "Hoan"
      }, {
        code: "2",
        value: "Trang"
      }]
    }
    this.lstCustomer = tmpCustomer.dataCustomer;
  }

  public resetClick() {
    this.portalStatus = "";
    this.scheduleNo = "";
    this.customerId = [];
    this.clientAccountId = [];
    this.customerId = [];
    this.invoiceNo = "";
  }

  public searchCA() {
    let x = {
      filter: {
        client: this.clientId
      },
      page: 1,
      size: 20
    }

    this.pro.searchCA(x).subscribe((rsp: any) => {
      let item = {
        sfid: "",
        clientAccount: "-- Please select --"
      }

      if (rsp.status === HTTP.STATUS_SUCCESS) {

        rsp.result.data.unshift(item);
        this.lstCA = rsp.result.data;
      }
      else {
        this.lstCA.unshift(item);
      }
    }, (err) => {
      console.log(err);
    });
  }

  // serach function
  public searchClick() {
    this.search();
    // this.curentPage = page;
  }
  public search() {
  }
}

