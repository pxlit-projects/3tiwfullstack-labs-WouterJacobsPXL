import {Component, Input, input} from '@angular/core';
import {Pokemon} from "../shared/models/pokemon.model";
import {NgClass} from "@angular/common";

@Component({
  selector: 'app-pokemon',
  standalone: true,
  imports: [NgClass],
  templateUrl: './pokemon-item.component.html',
  styleUrl: './pokemon-item.component.css'
})
export class PokemonItemComponent {
  @Input() pokemon!: Pokemon;
}
