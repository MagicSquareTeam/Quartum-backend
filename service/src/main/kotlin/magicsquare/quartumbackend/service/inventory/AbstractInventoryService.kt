package magicsquare.quartumbackend.service.inventory

import magicsquare.quartumbackend.exceptions.InventoryServiceException
import magicsquare.quartumbackend.persistance.mapper.CommonMapper
import magicsquare.quartumbackend.persistance.repository.BaseJpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus.NOT_FOUND
import java.io.Serializable

abstract class AbstractInventoryService<D : Any, E : Any, S : Serializable>(
  private val repository: BaseJpaRepository<E, S>,
  private val mapper: CommonMapper<D, E>
) : CommonInventoryService<D, S> {
  override fun create(dto: D): D {
    val savedEntity = repository.save(mapper.toEntity(dto))
    return mapper.toDto(savedEntity)
  }

  override fun update(dto: D, id: S): D {
    val entity = repository.findByIdOrNull(id)
    return entity?.let {
      mapper.update(dto, it)
      val savedEntity = repository.save(it)
      mapper.toDto(savedEntity)
    } ?: throw InventoryServiceException("Не найдена запись с id = $id", NOT_FOUND)
  }

  override fun getById(id: S): D {
    val entity = repository.findByIdOrNull(id)
    return entity?.let {
      mapper.toDto(entity)
    } ?: throw InventoryServiceException("Не найдена запись с id = $id", NOT_FOUND)
  }

  override fun deleteById(id: S) {
    val entity = repository.findByIdOrNull(id)
    entity?.let {
      repository.deleteById(id)
    } ?: throw InventoryServiceException("Не найдена запись с id = $id", NOT_FOUND)
  }

  //TODO реализовать
//  override fun findAll(
//  ): List<D> {
//    val entity = repository.findAll()
//
////    entity?.let {
////
////    } ?: throw InventoryServiceException("Не найдена запись с id = $id", NOT_FOUND)
//  }
}
