# Micro servei trips
***
En aquest micro servei es gestionen els viatges que es realitzaran a la plataforma.
L'aplicació està basada en una arquitectura hexagonal, on els paquets estan separats per capes i seguint el patró de disseny DDD (Domain Driven Design).
La jerarquia dels paquets és la següent:

### Application
***
En aquest paquet es troben les classes que defineixen la lògica de negoci de l'aplicació. Aquestes classes són les defineixen cadascun dels casos d'ús de l'aplicació.
### Domain
***
En aquest paquet es troben les classes que defineixen el model domini de l'aplicació. En aquest paquet podem trobar els models de dades, la implementació dels serveis i la definició dels repositoris.
### Infrastructure
***
En aquest paquet es troben les classes que s'encarreguen d'interactuar amb l'exterior, tant d'entrada com de sortida. En aquest micro servei definim els controladors REST i les implementacions dels repositoris, cadascú amb els seus mappers específics per a convertir els objectes del domini a les entitats necessaries.