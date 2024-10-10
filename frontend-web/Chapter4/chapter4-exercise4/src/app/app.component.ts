import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {Article} from "./shared/models/Article.model";
import {ArticleComponentComponent} from "./article-component/article-component.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, ArticleComponentComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  articles!: Article[];

  ngOnInit() {
    this.articles = [
      new Article(1, "Tandenborstel", 1.5, 3.24),
      new Article(2, "Handdoek", 2.5, 4.75),
      new Article(3, "Washandje", 0.4, 1.20),
      new Article(4, "Keukenrol", 1.75, 0.65)
    ];
  }
}
