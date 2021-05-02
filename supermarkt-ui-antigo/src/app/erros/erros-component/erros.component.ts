import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-erros',
  templateUrl: './erros.component.html',
  styleUrls: ['./erros.component.scss']
})
export class ErrosComponent implements OnInit {
  routeParams;
  data;

  constructor(
    private activatedRoute: ActivatedRoute,
  ) { }

  ngOnInit(): void {
    this.routeParams = this.activatedRoute.snapshot.queryParams;
    this.data = this.activatedRoute.snapshot.data;
  }
}
