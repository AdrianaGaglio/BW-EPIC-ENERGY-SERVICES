import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-filters',
  templateUrl: './filters.component.html',
  styleUrl: './filters.component.scss',
})
export class FiltersComponent {
  @Output() sort = new EventEmitter<string>();
  @Output() order = new EventEmitter<string>();

  emitSort(sort: string) {
    this.sort.emit(sort);
  }

  emitOrder(order: string) {
    this.order.emit(order);
  }
}
