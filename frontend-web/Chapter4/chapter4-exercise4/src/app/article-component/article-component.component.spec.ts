import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArticleComponentComponent } from './article-component.component';

describe('ArticleComponentComponent', () => {
  let component: ArticleComponentComponent;
  let fixture: ComponentFixture<ArticleComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ArticleComponentComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ArticleComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
