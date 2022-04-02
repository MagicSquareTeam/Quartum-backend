package magicsquare.quartumbackend.service

interface CommonInventoryService<D, S> {
  fun create(dto: D): D
  fun update(dto: D, id: S): D
  fun getById(id: S): D
  fun deleteById(id: S)
  fun findAll(): List<D>
  fun findByCriteria(criteria: String, value: String, clazz: Class<S>) : S
}
