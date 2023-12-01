import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent {
  @Output() searching: EventEmitter<string> = new EventEmitter()
  constructor(private route: ActivatedRoute, private router: Router){

  }
  searchTerm: string = '';
  onSearch(): void {
    this.searching.emit(this.searchTerm)
}
}
