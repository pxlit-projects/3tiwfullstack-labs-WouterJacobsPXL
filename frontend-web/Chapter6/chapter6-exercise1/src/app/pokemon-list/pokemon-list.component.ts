import {Component, inject, OnInit} from '@angular/core';
import {Pokemon} from "../shared/models/pokemon.model";
import {PokemonItemComponent} from "../pokemon-item/pokemon-item.component";
import {NgClass} from "@angular/common";
import {PokemonService} from "../shared/services/pokemon-service";

@Component({
  selector: 'app-pokemon-list',
  standalone: true,
  imports: [
    PokemonItemComponent,
    NgClass
  ],
  templateUrl: './pokemon-list.component.html',
  styleUrl: './pokemon-list.component.css'
})
export class PokemonListComponent implements OnInit {
  pokemons!: Pokemon[];
  selectedPokemon?: Pokemon;
  pokemonService: PokemonService = inject(PokemonService);

  ngOnInit(): void {
    this.pokemons = this.pokemonService.getPokemons();
  }


  selectPokemon(item: Pokemon) {
    this.selectedPokemon = item
  }
}
