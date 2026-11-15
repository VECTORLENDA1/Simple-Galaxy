# Simple Galaxy

Mod para Minecraft **1.21.1 (NeoForge)**: uma dimensão galáctica com minérios novos, entidades divinas e história própria.
Pensado para combinar com o [Simple Ores](https://github.com/VECTORLENDA1/Simple_Ores_NeoForge), mas funciona sozinho.

## O que já existe

- **Dimensão "A Galáxia"** (`simplegalaxy:galaxy`): noite estrelada permanente, terreno seco de Pedra Cósmica coberto de Regolito Astral, com grutas.
  - Biomas: Planícies Estreladas e Campos de Nebulosa.
  - Entrada provisória: **Bússola Astral** (clique direito para ir e voltar).
- **Remakes dos minérios vanilla** em Pedra Cósmica (ferro, ouro, diamante, esmeralda), mais ricos do que os do Overworld.
- **Minérios novos**: Pó de Estrela, Nebulite (só nos Campos de Nebulosa) e Empyrium (raro, no fundo).

## Por fazer

- História (com vozes), entidades divinas, outras entidades, portal definitivo.
- Texturas finais: as atuais são provisórias.

## Desenvolvimento

- `./gradlew runClient`: abre o jogo com o mod.
- `./gradlew runData`: volta a gerar modelos, loot tables, receitas, tags e worldgen em `src/generated/resources`
  (correr sempre que se mexe em `datagen/` ou `worldgen/`).
- `./gradlew build`: cria o `.jar` em `build/libs`.

Onde está cada coisa:

| O quê | Onde |
|---|---|
| Blocos e itens | `block/ModBlocks.java`, `item/ModItems.java` |
| Minérios (forma e altura dos veios) | `worldgen/ModConfiguredFeatures.java`, `worldgen/ModPlacedFeatures.java` |
| Biomas | `worldgen/ModBiomes.java` |
| Dimensão e terreno | `src/main/resources/data/simplegalaxy/dimension*`, `worldgen/noise_settings` |
| Céu da dimensão | `client/GalaxyEffects.java` |
| Traduções (EN e PT) | `src/main/resources/assets/simplegalaxy/lang` |
