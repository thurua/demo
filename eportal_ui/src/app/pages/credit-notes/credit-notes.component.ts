import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { HTTP, Utils } from '../../utilities/utility';
import { ScheduleProvider } from '../../providers/schedule';

@Component({
  selector: 'app-credit-notes',
  templateUrl: './credit-notes.component.html',
  styleUrls: ['./credit-notes.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class CreditNotesComponent implements OnInit {
  public portalStatus: string = "";
  public customer: string = "";
  public lstCustomer: any[] = [];
  public lstStatus: any[] = [];
  public lstCA: any[] = [];
  public data = [];
  public clientId: string = "";
  public clientName: string = "";
  public clientAccountId = [];
  public customerId = [];
  public total: number = 0;
  public fromDate = new Date();
  public toDate = new Date();
  public minDate = new Date();
  public maxDate = new Date();
  public scheduleNo = "";
  public creditNoteNo = "";

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
        title: 'Credit Note No.',
        filter: false,
        type: 'html',
        valuePrepareFunction: (cell, row) => {
          return `<a href="/#/pages/credit-notes-details/${row.id}">${row.creditNoteNo}</a>`
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
      customer: {
        title: 'Customer.',
        type: 'string',
        filter: false
      },
      creditNoteDate: {
        title: 'Credit Note Date',
        type: 'string',
        filter: false
      },
      creditNoteAmount: {
        title: 'Credit Note Amount',
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
        value: "Reassigned"
      }, {
        code: "4",
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
    this.clientAccountId = [];
    this.customerId = [];
    this.scheduleNo = "";
    this.creditNoteNo = "";
    this.data = [];
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
