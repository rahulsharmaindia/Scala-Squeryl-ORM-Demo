package code

import org.squeryl.annotations._


object OrderStatus extends Enumeration {
  val PAYMENT_PENDING   = Value(10, "payment_pending")
  val PAYMENT_FAILED    = Value(20, "payment_failed")
  val ORDERED           = Value(30, "ordered")
  val PICKING           = Value(40, "picking")
  val DELIVERY_PARTIAL  = Value(50, "delivery_partial")
  val DELIVERY_COMPLETE = Value(60, "delivery_complete")
  val CANCELLED         = Value(70, "cancelled")

  def fromString(s: String): OrderStatus.Value = {
    // Do not allow un-defined states: throws exception on unmapped states
    try {
      OrderStatus.withName(s)
    } catch {
      case e: Exception =>
        throw new RuntimeException("Unknown status for order: '" + s + "'")
    }
  }
}

/**
  * Order contents.
  *
  * Note: Saving monetary amounts as string,
  *       double / float types are not to be used with
  *       them as rounding is absolutely not wanted.
  */
class OrderData(@Column              var transactionId: Long,
                @Column(length=64)   var bookingId: String,
                @Column(length=4096) var comments: String,
                @Column(length=32)   var status: String,
                @Column              var orderTimestamp: Long,
                // Payment specific
                @Column(length=64)   var paymentMethod: String,
                @Column(length=64)   var paymentReferenceId: String,
                @Column              var paymentTimestamp: Option[Long],
                @Column(length=8)    var currency: String,
                @Column(length=64)   var totalPrice: String,
                @Column(length=64)   var totalExVat: String,
                @Column(length=64)   var totalVat: String,
                // Customer specific
                @Column(length=64)   var firstName: String,
                @Column(length=64)   var lastName: String,
                @Column(length=256)  var email: String,
                @Column(length=64)   var phone: String,
                @Column(length=16)   var clubId: String, // VL Club number is 7 digits(?)
                @Column(length=16)   var locale: String, // Customer language
                // Delivery specific
                // NOTE: Delivery means journey start (e.g. departure from HEL)
                @Column(length=16)   var outBoundShip: String,
                @Column(length=16)   var outBoundDeparturePort: String,
                @Column              var outBoundDepartureTime: Long,
                // NOTE: home bound means trip to home start (e.g. departure from TAL)
                @Column(length=16)   var homeBoundShip: String,
                @Column(length=16)   var homeBoundDeparturePort: String,
                @Column              var homeBoundDepartureTime: Long,
                @Column(length=16)   var deliveryMethod: String, // car, van, cart
                @Column(length=256)  var deliveryMethodDetails: String, // car license number, model, color
                @Column(length=16)   var merchantId: String
               ) extends BaseEntity {
  // For Squeryl to determine data types.
  def this() = this(0L, // transactionId
    "", // bookingId
    "", // comments
    "ordered", // status
    0L, // orderTimestamp
    "",       // paymentMethod
    "",       // paymentReferenceId
    Some(0L), // paymentTimestamp
    "",       // currency
    "",       // totalPrice
    "",       // totalExVat
    "",       // totalVat
    "rahul", // firstName
    "", // lastName
    "", // email
    "", // phone
    "", // clubId
    "fi", // locale
    "", // outBoundShip
    "", // outBoundDeparturePort
    0L, // outBoundDepartureTime
    "", // homeBoundShip
    "", // homeBoundDeparturePort
    0L, // homeBoundDepartureTime
    "", // deliveryMethod
    "",  // deliveryMethodDetails
    ""   //merchantId
  )
}


