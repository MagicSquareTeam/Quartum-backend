package magicsquare.quartumbackend.service.inventory

interface CommonInventoryService<D, S> {
  fun create(dto: D): D
  fun update(dto: D, id: S): D
  fun getById(id: S): D
  fun deleteById(id: S)
}
