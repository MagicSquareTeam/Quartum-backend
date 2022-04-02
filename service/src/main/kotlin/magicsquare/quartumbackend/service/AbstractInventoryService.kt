package magicsquare.quartumbackend.service

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import magicsquare.quartumbackend.exceptions.InventoryServiceException
import magicsquare.quartumbackend.persistance.mapper.CommonMapper
import magicsquare.quartumbackend.persistance.repository.BaseJpaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.stereotype.Service
import java.io.Serializable


abstract class AbstractInventoryService<D : Any, E : Any, S : Serializable>(
  val repository: BaseJpaRepository<E, S>,
  val mapper: CommonMapper<D, E>,
  open val entityManager: EntityManager
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

  override fun findAll(
  ): List<D> {
    val entities = repository.findAll()
    entities.takeIf { it.isEmpty()} ?:
      throw InventoryServiceException("Не найдены записи %s".format(entities::class.qualifiedName), NOT_FOUND)
    return entities.stream()
      .map { mapper.toDto(it) }
      .toList()
  }

  override fun findByCriteria(criteria: String, value: String, clazz: Class<S>): S {
    val criteriaBuilder = entityManager.criteriaBuilder
    val criteriaQuery = criteriaBuilder.createQuery(clazz)

    val root = criteriaQuery.from(clazz)

    val query = criteriaQuery.select(root).where(criteriaBuilder.equal(root.get<String>(criteria), value))

    return entityManager.createQuery(query).singleResult
  }
}