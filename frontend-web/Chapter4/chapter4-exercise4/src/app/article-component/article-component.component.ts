import {Component, Input} from '@angular/core';
import {Article} from "../shared/models/Article.model";
import {CurrencyPipe} from "@angular/common";

@Component({
  selector: 'app-article-component',
  standalone: true,
  imports: [
    CurrencyPipe
  ],
  templateUrl: './article-component.component.html',
  styleUrl: './article-component.component.css'
})
export class ArticleComponentComponent {
  @Input() articles!: Article[];

  showAlert(article: Article) {
    alert(`Item: ${article.artikelNaam} - Sale price: ${article.verkoopPrijs} - Purchase price: ${article.aankoopPrijs}`);
  }
}
