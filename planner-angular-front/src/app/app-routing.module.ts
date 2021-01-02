import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { SubtopicsComponent } from './subtopics/subtopics.component';
import { TopicsComponent } from './topics/topics.component';
import { UpdateTopicComponent } from './update-topic/update-topic.component';

const routes: Routes = [
  { path: '', redirectTo: '/topics',  pathMatch: 'full'},
  { path: 'subtopics/topic/:id', component: SubtopicsComponent},
  { path: 'topics', component: TopicsComponent },
  { path: 'topics/:id', component: UpdateTopicComponent }
]

@NgModule({
  declarations: [],
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule { }
