import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { ScheduleProvider } from '../../providers/schedule';
import { ActivatedRoute, Params } from '@angular/router';
import { HTTP } from '../../utilities/const';

@Component({
  selector: 'app-credit-notes-details',
  templateUrl: './credit-notes-details.component.html',
  styleUrls: ['./credit-notes-details.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class CreditNotesDetailsComponent implements OnInit {
  public dataReason = [];
  public loader = true; // check Reason Section Show
  public data = {
    "clientName": "",
    "clientAccount": "",
    "creditNoteNo": "",
    "customer": "",
    "currency": "",
    "appliedInvoice": "",
    "clientMarks": "",
    "opsMarks": "",
    "status": "",
    "scheduleOfOffer": "",
    "creditNoteDate": "",
    "customerBranch": "",
    "creditAmount": "",
    "outstandingAmount": ""
  };

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
      sNo: {
        title: 'S.No',
        filter: false,
        type: 'html',
        valuePrepareFunction: (cell, row) => {
          return `<a href="/#/pages/credit-notes-details/${row.id}">${row.sNo}</a>`
        },
      },
      reason: {
        title: 'Reason',
        type: 'string',
        filter: false
      },
      date: {
        title: 'Date',
        type: 'string',
        filter: false
      },
      Amount: {
        title: 'Amount',
        type: 'string',
        filter: false
      }
    }
  };
  constructor() {

  }

  ngOnInit() {

  }

}
