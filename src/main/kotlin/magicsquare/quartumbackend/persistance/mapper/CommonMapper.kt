package magicsquare.quartumbackend.persistance.mapper

/**
 * Common mapper - интейрфейс для мапперов, который объединяет в себе основные методы
 *
 * @param D - класс представляютщий dto
 * @param E - класс представляютщий сушность
 */
interface CommonMapper<D : Any, E : Any> {
  /**
   * Конвертирует сущность в dto
   *
   * @param entity
   * @return dto
   */
  fun toDto(entity: E): D

  /**
   * обноволяет сущность
   *
   * @param dto
   * @param entity
   */
  fun update(dto: D, entity: E)

  /**
   * Конвертирует dto в сущность
   *
   * @param dto
   * @return сущность
   */
  fun toEntity(dto: D): E
}
