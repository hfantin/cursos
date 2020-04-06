import { NgModule } from '@angular/core';
import { LoadButtonComponent } from './load-button/load-button.component';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';

import { PhotoListComponent } from './photo-list.component';
import { PhotosComponent } from './photos/photos.component';
import { PhotoModule } from '../photo/photo.mudule';
import { PhotoFormModule } from '../photo-form/photo-form.module';
import { FilterByDescription } from './filter-by-description.pipe';
import { CardModule } from 'src/app/shared/components/card/card.module';
import { SearchComponent } from './search/search.component';
import { DarkenOnHoverModule } from 'src/app/shared/directives/darken-on-hover/darken-on-hover.module';

@NgModule({
    declarations: [
        PhotoListComponent,
        PhotosComponent,
        LoadButtonComponent, 
        FilterByDescription,
        SearchComponent 
    ],
    imports: [
        CommonModule,
        PhotoModule, 
        CardModule,
        DarkenOnHoverModule
    ]
})
export class PhotoListModule {}