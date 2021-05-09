import { NgModule } from '@angular/core';

import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import { MatTableModule } from '@angular/material/table';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatTabsModule } from '@angular/material/tabs';
import { MatPaginatorModule } from "@angular/material/paginator";
import { MatToolbarModule } from "@angular/material/toolbar";
import { MatExpansionModule } from "@angular/material/expansion";
import { MatMenuModule } from "@angular/material/menu";
import { MatSelectModule } from "@angular/material/select";

@NgModule({
  imports: [
    MatButtonModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatPaginatorModule,
    MatTableModule,
    MatToolbarModule,
    MatTabsModule,
    MatExpansionModule,
    MatMenuModule,
    MatSelectModule
  ],
  exports: [
    MatButtonModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatPaginatorModule,
    MatTableModule,
    MatToolbarModule,
    MatTabsModule,
    MatExpansionModule,
    MatMenuModule,
    MatSelectModule,
  ]
})
export class MaterialModule {}
