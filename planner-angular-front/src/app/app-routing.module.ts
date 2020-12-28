import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { SubtopicsComponent } from './subtopics/subtopics.component';

const routes: Routes = [
  { path: 'subtopics/topic/:id', component: SubtopicsComponent}
]

@NgModule({
  declarations: [],
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule { }
