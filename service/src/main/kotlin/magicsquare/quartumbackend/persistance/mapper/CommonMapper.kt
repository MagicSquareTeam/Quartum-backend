package magicsquare.quartumbackend.persistance.mapper

import java.time.ZonedDateTime

interface CommonMapper<D : Any, E : Any> {
  fun toDto(entity: E): D
  fun update(dto: D, entity: E)
  fun toEntity(dto: D): E
}
