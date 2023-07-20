import com.android.volley.NetworkResponse
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.HttpHeaderParser
import com.android.volley.toolbox.Volley
import java.io.ByteArrayOutputStream
import java.io.DataOutputStream
import java.io.IOException
import java.util.*

open class VolleyMultipartRequest(
    method: Int,
    url: String,
    private val mListener: Response.Listener<NetworkResponse>,
    errorListener: Response.ErrorListener
) : Request<NetworkResponse>(method, url, errorListener) {

    private val mBoundary = "*****"
    private val mLineEnd = "\r\n"
    private val mTwoHyphens = "--"

    private val mHeaders: MutableMap<String, String> = HashMap()
    private val mParts: MutableList<Part> = ArrayList()

    override fun getHeaders(): MutableMap<String, String> {
        return mHeaders
    }

    override fun getBodyContentType(): String {
        return "multipart/form-data;boundary=$mBoundary"
    }

    override fun getBody(): ByteArray {
        val bos = ByteArrayOutputStream()
        val dos = DataOutputStream(bos)

        try {
            // Agregar las partes al cuerpo de la solicitud
            for (part in mParts) {
                buildPart(dos, part)
            }

            // Cerrar el cuerpo de la solicitud
            dos.writeBytes(mTwoHyphens + mBoundary + mTwoHyphens + mLineEnd)

            dos.flush()
            dos.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return bos.toByteArray()
    }

    override fun parseNetworkResponse(response: NetworkResponse): Response<NetworkResponse> {
        return Response.success(
            response,
            HttpHeaderParser.parseCacheHeaders(response)
        )
    }

    override fun deliverResponse(response: NetworkResponse) {
        mListener.onResponse(response)
    }

    fun addPart(part: Part) {
        mParts.add(part)
    }

    private fun buildPart(dos: DataOutputStream, part: Part) {
        try {
            dos.writeBytes(mTwoHyphens + mBoundary + mLineEnd)
            dos.writeBytes("Content-Disposition: form-data; name=\"${part.name}\"; filename=\"${part.fileName}\"" + mLineEnd)
            dos.writeBytes("Content-Type: ${part.mimeType}" + mLineEnd)
            dos.writeBytes(mLineEnd)

            dos.write(part.data)

            dos.writeBytes(mLineEnd)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    data class Part(val name: String, val fileName: String, val data: ByteArray, val mimeType: String)
}
